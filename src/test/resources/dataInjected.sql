/* Table users */
INSERT INTO users (username,password,fullname,role) VALUES ("admin", "$2a$10$Kxg5wjX6DE.zk/ZxjEVDPO5X6rExgCogq4PM2K0moDUm/GUKp6jKu", "Jerome", "ADMIN");
INSERT INTO users (username,password,fullname,role) VALUES ("user", "$2a$10$Kxg5wjX6DE.zk/ZxjEVDPO5X4rExgCfRq4PM2K0moDUm/SUKp6jKj", "Teddy", "USER");

/* Table trade*/
ALTER TABLE trade MODIFY trade_id int NOT NULL AUTO_INCREMENT;
INSERT INTO trade (account, type, buy_quantity) VALUES ("Trade1", "TradeClass1", 10.0);
INSERT INTO trade (account, type, buy_quantity) VALUES ("Trade2", "TradeClass2", 20.0);
INSERT INTO trade (account, type, buy_quantity) VALUES ("Trade3", "TradeClass3", 30.0);
INSERT INTO trade (account, type, buy_quantity) VALUES ("Trade4", "TradeClass4", 40.0);

/* rulename */
ALTER TABLE rulename MODIFY id int NOT NULL AUTO_INCREMENT;
INSERT INTO rulename(name, description) VALUES ("rulename1", "Description1");
INSERT INTO rulename(name, description) VALUES ("rulename2", "Description2");
INSERT INTO rulename(name, description) VALUES ("rulename3", "Description3");

/* Table rating */
ALTER TABLE rating MODIFY id int NOT NULL AUTO_INCREMENT;
INSERT INTO rating(moodys_rating, sandprating) VALUES ("moodysRating1","sandPRating1");
INSERT INTO rating(moodys_rating, sandprating) VALUES ("moodysRating2","sandPRating2");
INSERT INTO rating(moodys_rating, sandprating) VALUES ("moodysRating3","sandPRating3");
INSERT INTO rating(moodys_rating, sandprating) VALUES ("moodysRating4","sandPRating4");

/* Table curvepoint */
ALTER TABLE curvepoint MODIFY id int NOT NULL AUTO_INCREMENT;
INSERT INTO curvepoint(curve_id,term,value) VALUES (1,8.00, 7.50);
INSERT INTO curvepoint(curve_id,term,value) VALUES (2,18.00, 17.50);
INSERT INTO curvepoint(curve_id,term,value) VALUES (3,28.00, 10.00);

/* Table bidlist */
ALTER TABLE bidlist MODIFY bid_list_id int NOT NULL AUTO_INCREMENT;
INSERT INTO bidlist(account,type,bid_quantity) VALUES ("Account1", "type1", 5.00);
INSERT INTO bidlist(account,type,bid_quantity) VALUES ("Account2", "type2", 15.00);
INSERT INTO bidlist(account,type,bid_quantity) VALUES ("Account3", "type3", 25.00);
INSERT INTO bidlist(account,type,bid_quantity) VALUES ("Account4", "type4", 35.00);
