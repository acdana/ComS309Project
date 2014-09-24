CREATE TABLE User (
username CHAR(20) NOT NULL,
password CHAR(20) NOT NULL,
email CHAR(30) NOT NULL,
userType CHAR(10) NOT NULL,     #Basic, Moderator, Admin
penalties INTEGER NOT NULL,     #Number of penalties that a user has for bad behavior, not showing up to trades, etc.
accountStatus CHAR(10) NOT NULL, #Whether or not the account is open, banned, temporarily disables, etc.
PRIMARY KEY (username)
)

CREATE TABLE Profile (
username CHAR(20) NOT NULL,
profilePicture CHAR(30),        #file path for the photo (still not sure about this)
reputation INTEGER NOT NULL,    #reputation is gained by completing trades. Starts at 0
bio CHAR(100),
FOREIGN KEY(username) REFERENCES User(username),
PRIMARY KEY (username)
)

CREATE TABLE Message (
username CHAR(20) NOT NULL,     #username of the person who the message is intended for
message CHAR(100) NOT NULL,                     #message contents (may expand this to a larger size)
sender CHAR(20) NOT NULL,       #username of the person sending the message
dateSent DATE NOT NULL,
dateOpened DATE,
FOREIGN KEY(username) REFERENCES User(username),
FOREIGN KEY(sender) REFERENCES User(username),
PRIMARY KEY (username)
)

CREATE TABLE Sale (
saleID INTEGER NOT NULL,                  #Unique ID for a sale so that it can be referenced later.
primarySeller CHAR(20) NOT NULL,          #Person who posted the sale
secondarySeller CHAR(20) NOT NULL,        #Once a trade is accpeted the second trader's username goes here. May need to be filled with an EMPTY user at beginning
primarySellerLocation CHAR(20),
secondarySellerLocation CHAR(20),
chosenLocation CHAR(20),                       #After location is selected update this with the coordinates
dateCreated DATE NOT NULL,
FOREIGN KEY(primarySeller) REFERENCES User(username),
FOREIGN KEY(secondarySeller) REFERENCES User(username),
PRIMARY KEY (saleID)
)

CREATE TABLE Item (
itemID INTEGER NOT NULL,
saleID INTEGER NOT NULL,                         #When Item.saleID = Sale.saleID, all Item.itemName are names of items that took place in that unique sale.
username CHAR(20) NOT NULL,      #The username of the person who put the item up for trade
itemName CHAR(40) NOT NULL,                       #Name of the item being sold
FOREIGN KEY(username) REFERENCES User(username),
PRIMARY KEY (itemID)
)
