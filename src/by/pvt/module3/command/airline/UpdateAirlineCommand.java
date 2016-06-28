package by.pvt.module3.command.airline;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.AirlineDAO;
import by.pvt.module3.entity.Airline;
import by.pvt.module3.resource.ConfigurationManager;

public class UpdateAirlineCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        Airline air = new Airline();
        air.setId(Integer.parseInt(request.getParameter(Airline.ID).trim()));
        air.setName(request.getParameter(Airline.NAME).trim());
        
		try {
            AirlineDAO.getInstance().updateAirline(air);

			List<Airline> list = AirlineDAO.getInstance().getAllAirlines();
			request.setAttribute("airline", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ConfigurationManager.getProperty("path.page.airlines");
    }
}
