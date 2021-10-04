package by.epam.litvinko.beautysalon.controller.command.impl.admin;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.entity.ProvideService;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.model.service.ShopService;
import by.epam.litvinko.beautysalon.model.service.impl.ShopServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.EXCEPTION;

public class AddProductCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddProductCommand.class);
    private final ShopService service = new ShopServiceImpl();
    private static final String UPLOAD_DIR = "uploads";
    private static final String EMPTY_STRING = "";

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router;
        String local = (String) request.getSession().getAttribute(LOCALE);
        String name = request.getParameter(NEW_PRODUCT_NAME);
        String description = request.getParameter(NEW_PRODUCT_DESCRIPTION);
        String price = request.getParameter(NEW_PRODUCT_PRICE);
        String serviceTime = request.getParameter(PRODUCT_SERVICE_TIME);
        String category = request.getParameter(SELECT_CATEGORY_ID);
        ProvideService newProduct = ProvideService.newBuilder()
                .setCategoryId(Integer.parseInt(category))
                .setName(name)
                .setDescription(description)
                .setPrice(BigDecimal.valueOf(Double.parseDouble(price)))
                .setServiceTime(Integer.parseInt(serviceTime))
                .build();
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
                newProduct.setImage(fileName);
                if (service.createProduct(newProduct)){
                    request.getSession().setAttribute(SUCCESS_OPERATION, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(SUCCESS_OPERATION_PATH));
                }else {
                    request.getSession().setAttribute(FAILED_OPERATION, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(FAILED_OPERATION_PATH));
                }
            }
            router = new Router(ADMIN_JSP, Router.RouterType.REDIRECT);
        } catch (ServletException | IOException | ServiceException e) {
            logger.error("Error at add product command", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }
        return router;

    }

}
