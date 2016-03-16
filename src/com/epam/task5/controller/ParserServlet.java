package com.epam.task5.controller;

import com.epam.task5.builder.AbstractCardsBuilder;
import com.epam.task5.entity.Card;
import com.epam.task5.factory.CardBuilderFactory;
import com.epam.task5.sort.CardTypeSort;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Никита on 05.01.2016.
 */
@WebServlet("/epam")
public class ParserServlet extends HttpServlet {
    static final Logger logger = LogManager.getLogger(ParserServlet.class);
    private AbstractCardsBuilder cardsBuilder;
    private CardBuilderFactory builderFactory = new CardBuilderFactory();
    private CardTypeSort cardTypeSort = new CardTypeSort();
    private Map<String, HashSet<Card>> map;

    @Override
    public void init() throws ServletException {
        String prefix = getServletContext().getRealPath("/");
        String fileName = getServletContext().getInitParameter("log4j");
        if(fileName != null) {
            PropertyConfigurator.configure(prefix + fileName);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, RuntimeException {
        String typeParser = req.getParameter("type");
        String realPath = getServletContext().getRealPath("/");
        String fullPath = realPath + "resources/cards.xml";
        // logger.info("sdfsd");
        cardsBuilder = builderFactory.createCardBuilder(typeParser);
        cardsBuilder.buildSetCards(fullPath);
        map = cardTypeSort.sortByCardType(cardsBuilder.getCards());
        req.setAttribute("greetingCard", map.get("GreetingCard"));
        req.setAttribute("musicCard", map.get("MusicCard"));
        req.setAttribute("promotionalCard", map.get("PromotionalCard"));
        req.setAttribute("name", typeParser.toUpperCase());
        req.getRequestDispatcher("/jsp/result.jsp").forward(req, resp);
    }
}
