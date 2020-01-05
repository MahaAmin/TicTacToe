CREATE TABLE `tictactoe`.`players` (
  `player_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `score` INT DEFAULT 0,
  `status` ENUM('0', '1', '2') NOT NULL DEFAULT '0',
  `avatar` VARCHAR(200),
  PRIMARY KEY (`player_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));


CREATE TABLE `tictactoe`.`games` ( 
`player1` INT NOT NULL , 
`player2` INT NOT NULL , 
`winner` INT default NULL , 
`date` TIMESTAMP NOT NULL , 
`game_status` ENUM('0','1','2') NOT NULL DEFAULT '0' ,
`game_board` VARCHAR(9) NOT NULL DEFAULT '000000000',
	foreign key(`player1`) references players(player_id),
    foreign key(`player2`) references players(player_id),
	foreign key(`winner`) references players(player_id)
 ) 