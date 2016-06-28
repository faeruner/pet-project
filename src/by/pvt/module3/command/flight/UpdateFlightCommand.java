package by.pvt.module3.command.flight;

import java.sql.SQLException;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.FlightDAO;
import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.entity.Airline;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Flight;
import by.pvt.module3.resource.ConfigurationManager;

public class UpdateFlightCommand implements ActionCommand {

	private static final DateFormat DF = new SimpleDateFormat("dd.MM.yyyy");

	@Override
	public String execute(HttpServletRequest request) {
		try {
			Flight flight = new Flight();
			flight.setId(Integer.parseInt(request.getParameter(Flight.ID).trim()));

			flight.setCode(request.getParameter(Flight.CODE).trim());
			flight.setDepDate(DF.parse(request.getParameter(Flight.DEPARTURE_DATE).trim()));
			flight.setReturnDate(DF.parse(request.getParameter(Flight.RETURN_DATE).trim()));
			flight.setCreateDate(DF.parse(request.getParameter(Flight.CREATE_DATE).trim()));

			flight.setArrival(new Airport());
			flight.getArrival().setId(Integer.parseInt(request.getParameter(Flight.AIRPORT_ARV_ID).trim()));

			flight.setDeparture(new Airport());
			flight.getDeparture().setId(Integer.parseInt(request.getParameter(Flight.AIRPORT_DEP_ID).trim()));

			flight.setAirline(new Airline());
			flight.getAirline().setId(Integer.parseInt(request.getParameter(Flight.AIRLINE_ID).trim()));

			flight.setCrew(new Crew());
			flight.getCrew().setId(Integer.parseInt(request.getParameter(Flight.CREW_ID).trim()));

			HttpSession session = request.getSession();
			Integer user_id = (Integer) session.getAttribute("user_id");
			flight.setUser(UserDAO.getInstance().getUser(user_id));
			FlightDAO.getInstance().updateFlight(flight);

			List<Flight> list = FlightDAO.getInstance().getAllFlights();
			request.setAttribute("flight", list);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ConfigurationManager.getProperty("path.page.flights");
	}
}
