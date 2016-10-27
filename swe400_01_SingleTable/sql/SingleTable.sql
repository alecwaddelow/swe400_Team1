DROP TABLE IF EXISTS InventoryItem;

CREATE TABLE InventoryItem(
id INT PRIMARY KEY NOT NULL auto_increment,
upc VARCHAR(25),
manufacturerID INT,
description VARCHAR(100),
batteryPowered bool,
length DOUBLE,
numberInStrip INT,
numberInBox INT,
className VARCHAR(25));
