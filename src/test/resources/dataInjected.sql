/* Table users */
INSERT INTO users (username,password,fullname,role) VALUES ("admin", "$2a$10$Kxg5wjX6DE.zk/ZxjEVDPO5X6rExgCogq4PM2K0moDUm/GUKp6jKu", "Jerome", "ADMIN");
INSERT INTO users (username,password,fullname,role) VALUES ("user", "$2a$10$Kxg5wjX6DE.zk/ZxjEVDPO5X4rExgCfRq4PM2K0moDUm/SUKp6jKj", "Teddy", "USER");

/* Table trade */
INSERT INTO trade (account, type, buyQuantity) VALUES ("Trade1", "TradeClass1", 10.0);
INSERT INTO trade (account, type, buyQuantity) VALUES ("Trade2", "TradeClass2", 20.0);
INSERT INTO trade (account, type, buyQuantity) VALUES ("Trade3", "TradeClass3", 30.0);
INSERT INTO trade (account, type, buyQuantity) VALUES ("Trade4", "TradeClass4", 40.0);

/* rulename */
INSERT INTO rulename(name, description) VALUES ("rulename1", "Description1");
INSERT INTO rulename(name, description) VALUES ("rulename2", "Description2");
INSERT INTO rulename(name, description) VALUES ("rulename3", "Description3");

/* Table rating */
INSERT INTO rating(moodysRating, sandPRating) VALUES ("moodysRating1","sandPRating1");
INSERT INTO rating(moodysRating, sandPRating) VALUES ("moodysRating2","sandPRating2");
INSERT INTO rating(moodysRating, sandPRating) VALUES ("moodysRating3","sandPRating3");
INSERT INTO rating(moodysRating, sandPRating) VALUES ("moodysRating4","sandPRating4");

/* Table curvepoint */
INSERT INTO curvepoint(curveId,term,value) VALUES (1,8.00, 7.50);
INSERT INTO curvepoint(curveId,term,value) VALUES (2,18.00, 17.50);
INSERT INTO curvepoint(curveId,term,value) VALUES (3,28.00, 10.00);

/* Table bidlist */
INSERT INTO bidlist(account,type,bidQuantity) VALUES ("Account1", "type1", 5.00);
INSERT INTO bidlist(account,type,bidQuantity) VALUES ("Account2", "type2", 15.00);
INSERT INTO bidlist(account,type,bidQuantity) VALUES ("Account3", "type3", 25.00);
INSERT INTO bidlist(account,type,bidQuantity) VALUES ("Account4", "type4", 35.00);
