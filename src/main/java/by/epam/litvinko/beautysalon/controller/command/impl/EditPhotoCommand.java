package by.epam.litvinko.beautysalon.controller.command.impl;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.model.service.ClientService;
import by.epam.litvinko.beautysalon.model.service.UserService;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.impl.ClientServiceImpl;
import by.epam.litvinko.beautysalon.model.service.impl.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.ERROR_JSP;
import static by.epam.litvinko.beautysalon.controller.command.PagePath.PROFILE_JSP;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;

/**
 * The type Edit photo command.
 */
public class EditPhotoCommand implements Command {

    private static final Logger logger = LogManager.getLogger(EditPhotoCommand.class);
    private static final String UPLOAD_DIR = "uploads";
    private static final String EMPTY_STRING = "";

    @Override
    public Router execute(HttpServletRequest request)  {

        Router router;
        String local = (String) request.getSession().getAttribute(LOCALE);
        UserService userService = new UserServiceImpl();
        ClientService clientService = new ClientServiceImpl();
        String userId = request.getParameter(USER_ID);

        try {
            String applicationDir = request.getServletContext().getRealPath(EMPTY_STRING);
            String uploadFileDir = applicationDir + UPLOAD_DIR + File.separator;
            File fileSaveDir = new File(uploadFileDir);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            Part filePart = request.getPart(IMAGE);
            if (filePart != null) {
                String fileName = filePart.getSubmittedFileName();
                for (Part part : request.getParts()) {
                    part.write(uploadFileDir + fileName);
                }
                userService.updateUserPhoto(userId, fileName);
                Optional<ClientDto> optionalClientDto = clientService.findClientByUserId(userId);
                if (optionalClientDto.isPresent()){
                    ClientDto client = optionalClientDto.get();
                    request.getSession().setAttribute(USER, client);
                    request.getSession().setAttribute(ROLE, client.role());
                }else {
                     request.getSession().setAttribute(EDIT_USER_DATA_ERROR, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(EDIT_USER_DATA_ERROR_PATH));
                }
            }
            router = new Router(PROFILE_JSP, Router.RouterType.REDIRECT);
        } catch (ServletException | IOException | ServiceException e) {
            logger.error("Error at edit photo command", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
