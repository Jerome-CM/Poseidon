package com.nnk.springboot;

import com.nnk.springboot.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class EqualsDomainTests {

    @Test
    void bidListTest(){
        BidList bid1 = new BidList();

        bid1.setBidListId(1);
        bid1.setAccount("Equals");
        bid1.setType("Type");
        bid1.setBidQuantity(10d);
        bid1.setAskQuantity(20d);
        bid1.setBid(15d);
        bid1.setAsk(12.50);
        bid1.setBenchmark("Bench");
        bid1.setBidListDate(new Timestamp(new Date().getTime()));
        bid1.setCommentary("Com");
        bid1.setSecurity("Security");
        bid1.setStatus("Stat");
        bid1.setTrader("trader");
        bid1.setBook("Book");
        bid1.setCreationName("NameCreation");
        bid1.setCreationDate(new Timestamp(new Date().getTime()));
        bid1.setRevisionName("Revision");
        bid1.setRevisionDate(new Timestamp(new Date().getTime()));
        bid1.setDealName("NameDeal");
        bid1.setDealType("Type for Deal");
        bid1.setSourceListId("Source");
        bid1.setSide("side");

        BidList bid2 = new BidList();

        bid2.setBidListId(1);
        bid2.setAccount("Equals");
        bid2.setType("Type");
        bid2.setBidQuantity(10d);
        bid2.setAskQuantity(20d);
        bid2.setBid(15d);
        bid2.setAsk(12.50);
        bid2.setBenchmark("Bench");
        bid2.setBidListDate(new Timestamp(new Date().getTime()));
        bid2.setCommentary("Com");
        bid2.setSecurity("Security");
        bid2.setStatus("Stat");
        bid2.setTrader("trader");
        bid2.setBook("Book");
        bid2.setCreationName("NameCreation");
        bid2.setCreationDate(new Timestamp(new Date().getTime()));
        bid2.setRevisionName("Revision");
        bid2.setRevisionDate(new Timestamp(new Date().getTime()));
        bid2.setDealName("NameDeal");
        bid2.setDealType("Type for Deal");
        bid2.setSourceListId("Source");
        bid2.setSide("side");

        assertEquals(bid1, bid2);
        assertEquals(bid1.hashCode(), bid2.hashCode());
    }

    @Test
    void curvePointTest(){

        CurvePoint curve1 = new CurvePoint();

        curve1.setCurveId(12);
        curve1.setAsOfDate(new Timestamp(new Date().getTime()));
        curve1.setTerm(20d);
        curve1.setValue(10d);
        curve1.setCreationDate(new Timestamp(new Date().getTime()));

        CurvePoint curve2 = new CurvePoint();

        curve2.setCurveId(12);
        curve2.setAsOfDate(new Timestamp(new Date().getTime()));
        curve2.setTerm(20d);
        curve2.setValue(10d);
        curve2.setCreationDate(new Timestamp(new Date().getTime()));

        assertEquals(curve1, curve2);
        assertEquals(curve1.hashCode(), curve2.hashCode());

    }

    @Test
    void ratingTest(){

        Rating rating1 = new Rating();

        rating1.setMoodysRating("Mood");
        rating1.setSandPRating("Sand");
        rating1.setFitchRating("Fitch");
        rating1.setOrderNumber(10);

        Rating rating2 = new Rating();

        rating2.setMoodysRating("Mood");
        rating2.setSandPRating("Sand");
        rating2.setFitchRating("Fitch");
        rating2.setOrderNumber(10);

        assertEquals(rating1, rating2);
        assertEquals(rating1.hashCode(), rating2.hashCode());
    }

    @Test
    void ruleNameTest(){

        RuleName rule1 = new RuleName();

        rule1.setName("Name");
        rule1.setDescription("description");
        rule1.setJson("json");
        rule1.setTemplate("template");
        rule1.setSqlStr("sqlStr");
        rule1.setSqlPart("sqlPart");

        RuleName rule2 = new RuleName();

        rule2.setName("Name");
        rule2.setDescription("description");
        rule2.setJson("json");
        rule2.setTemplate("template");
        rule2.setSqlStr("sqlStr");
        rule2.setSqlPart("sqlPart");

        assertEquals(rule1, rule2);
        assertEquals(rule1.hashCode(), rule2.hashCode());
    }

    @Test
    void tradeTest(){

        Trade trade1 = new Trade();

        trade1.setAccount("Account");
        trade1.setType("Type");
        trade1.setBuyQuantity(12d);
        trade1.setSellQuantity(75d);
        trade1.setBuyPrice(20d);
        trade1.setSellPrice(10d);
        trade1.setBenchmark("Bench");
        trade1.setTradeDate(new Timestamp(new Date().getTime()));
        trade1.setSecurity("Security");
        trade1.setStatus("Stat");
        trade1.setTrader("trader");
        trade1.setBook("Book");
        trade1.setCreationName("NameCreation");
        trade1.setCreationDate(new Timestamp(new Date().getTime()));
        trade1.setRevisionName("Revision");
        trade1.setRevisionDate(new Timestamp(new Date().getTime()));
        trade1.setDealName("NameDeal");
        trade1.setDealType("Type for Deal");
        trade1.setSourceListId("Source");
        trade1.setSide("side");

        Trade trade2 = new Trade();

        trade2.setAccount("Account");
        trade2.setType("Type");
        trade2.setBuyQuantity(12d);
        trade2.setSellQuantity(75d);
        trade2.setBuyPrice(20d);
        trade2.setSellPrice(10d);
        trade2.setBenchmark("Bench");
        trade2.setTradeDate(new Timestamp(new Date().getTime()));
        trade2.setSecurity("Security");
        trade2.setStatus("Stat");
        trade2.setTrader("trader");
        trade2.setBook("Book");
        trade2.setCreationName("NameCreation");
        trade2.setCreationDate(new Timestamp(new Date().getTime()));
        trade2.setRevisionName("Revision");
        trade2.setRevisionDate(new Timestamp(new Date().getTime()));
        trade2.setDealName("NameDeal");
        trade2.setDealType("Type for Deal");
        trade2.setSourceListId("Source");
        trade2.setSide("side");

        assertEquals(trade1, trade2);
        assertEquals(trade1.hashCode(), trade2.hashCode());

    }

    @Test
    void userTest(){

        User user1 = new User();

        user1.setUsername("Jerome");
        user1.setPassword("admin");
        user1.setFullname("Jérôme");
        user1.setRole("ADMIN");

        User user2 = new User();

        user2.setUsername("Jerome");
        user2.setPassword("admin");
        user2.setFullname("Jérôme");
        user2.setRole("ADMIN");

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

}
