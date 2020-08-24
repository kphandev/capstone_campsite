package com.techelevator.parks.model.jdbc;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.parks.model.Park;
import com.techelevator.parks.model.ParkDAO;

public class JDBCParkDAO implements ParkDAO{

	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCParkDAO (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private Park rowFromPark(SqlRowSet sqlPark) {
		Park newPark = new Park();
		newPark.setId(sqlPark.getLong("park_id"));
		newPark.setParkName(sqlPark.getString("name"));
		newPark.setParkLocation(sqlPark.getString("location"));
		newPark.setEstablishedYear(sqlPark.getDate("establish_date").toLocalDate());
		newPark.setArea(sqlPark.getLong("area"));
		newPark.setAnnualVisitors(sqlPark.getLong("visitors"));
		newPark.setDescription(sqlPark.getString("description"));
		
		return newPark;
	}
	
	@Override
	public List<Park> getAllParks() {
		ArrayList<Park> parkList = new ArrayList<Park>();
		String sqlGetPark = "SELECT * FROM park";
		SqlRowSet sqlPark = jdbcTemplate.queryForRowSet(sqlGetPark);
		while (sqlPark.next()) {
			Park newPark = rowFromPark(sqlPark);
			parkList.add(newPark);
		}
		return parkList;
	}

	@Override
	public Park getParkId(Long id) {
		ArrayList <Park> parkIdList = new ArrayList<Park>();
		String sqlParkId = "SELECT * FROM park WHERE park_id = ?";
		SqlRowSet sqParkIdRow = jdbcTemplate.queryForRowSet(sqlParkId, id);
		while (sqParkIdRow.next()) {
			Park newPark = rowFromPark(sqParkIdRow);
			parkIdList.add(newPark);
		}
		return null;
	}
	
	@Override
	public List<String> getNameByParkId() {
		List<String> results = new ArrayList<String>();
		
		String sql = "Select * from park WHERE park_id <= 5 ORDER BY name asc";
		SqlRowSet sqlNameId = jdbcTemplate.queryForRowSet(sql);
		while(sqlNameId.next()) {
			Park newPark = rowFromPark(sqlNameId);
			results.add(newPark.getParkName());
		}
		return results;
	}
	
	@Override
	public Park getParkByName(String parkName) {
		String sqlGetParkName = "SELECT * FROM park WHERE name = ?";
		SqlRowSet sqlParkName = jdbcTemplate.queryForRowSet(sqlGetParkName, parkName);
		
		sqlParkName.next();
			Park newPark = rowFromPark(sqlParkName);
		return newPark;
	}		
}
