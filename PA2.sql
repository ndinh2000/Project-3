DROP DATABASE IF EXISTS `petstore`;
CREATE DATABASE IF NOT EXISTS `petstore`;
USE `petstore`;

SET FOREIGN_KEY_CHECKS=0;	-- ???

--
-- Table structure for table `Pet`
--

CREATE TABLE `Pet` (
                       `pet_id` varchar(10) NOT NULL,
                       `name` varchar(255) NOT NULL,
                       `age` int NOT NULL,
                       `gender` enum('Male', 'Female'),
                       `price` float NOT NULL,
                       `message` varchar(1024) NOT NULL,
                       `profile_picture` varchar(2048) NOT NULL,
                       PRIMARY KEY (`pet_id`)
);


--
-- Table structure for table `Order`
--

# CREATE TABLE `Order` (
#                          `order_id` int NOT NULL AUTO_INCREMENT,
#                          `user_id` int NOT NULL,
#                          `pet_id` varchar(10) NOT NULL,
#                          `qty` int NOT NULL,
#                          `price` float NOT NULL,
#                          `name_first` varchar(50) NOT NULL,
#                          `name_last` varchar(50) NOT NULL,
#                          `email` varchar(100) NOT NULL,
#                          `address_country` varchar(30) NOT NULL,
#                          `address_zipcode` varchar(30) DEFAULT NULL,
#                          `address_state` varchar(30) DEFAULT NULL,
#                          `address_city` varchar(30) NOT NULL,
#                          `address_street` varchar(30) NOT NULL,
#                          `order_time` datetime NOT NULL,
#                          `card_number` varchar(30) NOT NULL,
#                          `expiration_date` datetime NOT NULL,
#                          shipping_method enum('ground', 'first_overnight', 'two_days'),
#                          PRIMARY KEY (`user_id`),
#                          CONSTRAINT `order_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `Pet` (`pet_id`) ON DELETE NO ACTION
# );

CREATE TABLE `Orders`(
                         `order_id` int NOT NULL AUTO_INCREMENT,
                         `user_id` int NOT NULL,
                         `pet_id` varchar(10) NOT NULL,
                         `qty` int NOT NULL,
                         `price` float NOT NULL,
                         `name_first` varchar(50) NOT NULL,
                         `name_last` varchar(50) NOT NULL,
                         `email` varchar(100) NOT NULL,
                         `address_zipcode` varchar(30) NOT NULL,
                         `address_state` varchar(30) NOT NULL,
                         `address` varchar(30) NOT NULL,
                         `card_number` varchar(30) NOT NULL,
                         `expiration_MM` varchar(5) NOT NULL,
                         `expiration_YY` varchar(5) NOT NULL,
                         shipping_method enum('ground', 'overnight', 'two_days'),
                         `paid` boolean not null default 0,
                         `phone_number` varchar(20) not null,
                         PRIMARY KEY (`order_id`),
                         foreign key (`pet_id`) references Pet(pet_id)
);

CREATE TABLE `Ratings` (
                           `user_id` int NOT NULL,
                           `pet_id` varchar(10) NOT NULL,
                           `rating` int NOT NULL,
                           CONSTRAINT `ratings_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `Pet` (`pet_id`) ON DELETE NO ACTION
);

INSERT INTO `Ratings` VALUES ('0', 'D101', '4');
INSERT INTO `Ratings` VALUES ('0', 'D102', '3');
INSERT INTO `Ratings` VALUES ('0', 'C101', '3');
INSERT INTO `Ratings` VALUES ('1', 'C101', '3');


# CREATE TABLE `Orders` (
#                          `order_id` int NOT NULL AUTO_INCREMENT,
#                          `user_id` int NOT NULL,
#                          `pet_id` varchar(10) NOT NULL,
#                          PRIMARY KEY (`order_id`),
#                          CONSTRAINT `order_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `Pet` (`pet_id`) ON DELETE NO ACTION
# );
#
# INSERT INTO `Orders` VALUES ('1', '1', 'D101');
# INSERT INTO `Orders` VALUES ('2', '1', 'D102');
# INSERT INTO `Orders` VALUES ('3', '1', 'C101');

# CREATE TABLE `ShopLogo` (
#                        `shop_logo` varchar(2048) NOT NULL
# );
#
# INSERT  INTO `ShopLogo` VALUES('./images/shopLogo.png');


# Filling Pet Table below:

INSERT INTO `Pet` VALUES ('D101', 'Lucky', 3, 'Male', 2000.0,
                          'I am a beautiful boy that needs a family that will
                    understand that I will need some time to adjust and settle
                    in my forever home. I will need a household, with everyone
                    over the age of 16, which has experience assisting with under
                     socialized dogs. I would be the perfect companion for those
                     that will want to work on building my confidence and to work
                     on making me become more independent.' ,
                          './images/DogImages/dog1.jfif');
INSERT INTO `Pet` VALUES ('D102', 'Willow', 5, 'Female', 15000.0,
                          'I am a sporty dog who would be the perfect outgoing
                    companion for an active family. I would love a family that
                    is over the age of 12 who are committed to training and
                    building the structure and routine that he needs to succeed.' ,
                          './images/DogImages/dog2.jfif');
INSERT INTO `Pet` VALUES ('D103', 'Marlow', 6, 'Male', 15000.0,
                          'I am looking for an active household with members over
                    the age of 18+. I would do best in a home who is committed to participating
                     in training to help build the structure and routine I need to
                      succeed. I will need a strong handler who knows how to set rules and
                       boundaries, but isn''t rough with me. I may have some handling
                     senstivities related to my collar so I should be trained to accept
                     handling. Positive reinforcement training and lots of mental and physical
                      exercises will help tailor me into the perfect companion
                      for a home who''d like me to be the center of their universe.' ,
                          './images/DogImages/dog3.jfif');
INSERT INTO `Pet` VALUES ('D104', 'Monroe', 6, 'Female', 12000.0,
                          'I am a timid girl who once she gets to know you enjoys walks
                    and affection. I am extremley undersocialized with dogs and would need
                    a home where I is the only dog. I am looking for a caring home with
                    members over the age of 12, that will help build my confidence and
                    understand I am a bit shy when meeting new people and uncomfortable in
                     novel situations.' ,
                          './images/DogImages/dog4.jfif');
INSERT INTO `Pet` VALUES ('D105', 'Avery', 2, 'Female', 10000.0,
                          'I need a home with members over the age of 12 who have experience
                     working with under- socialized dogs. I will need a nurturing home who
                      can offer me time, space and dedication towards working on my confidence
                       and social skills. I''m not a dog park dog but I love my inner-circle
                        of human friends. I will thrive in a home willing to engage in lots
                         of positive reinforcement training, mental and physical exercises.' ,
                          './images/DogImages/dog5.jfif');
INSERT INTO `Pet` VALUES ('D106', 'Spencer', 5, 'Male', 18000.0,
                          'I am initially timid but open up once I am in a calm and
                    patient household. Once comfortable, I will be zooming around
                    the yard and because I am always on the go, I will need leash
                    training. I would do best in a household with everyone over the age
                    of 12 who are going to be committed to training me and building the
                    structure and routine that I need.' ,
                          './images/DogImages/dog6.jfif');
INSERT INTO `Pet` VALUES ('D107', 'Dakota', 5, 'Female', 19000.0,
                          'I am either out running around in a yard or trying to grab
                    someone''s attention. I am not keen on being around other dogs so I
                    would need to be the only dog in the home. To set me up for success,
                    I am looking for a household with everyone being over the age of
                    18 who have experience with dogs and will commit to
                    training to help build structure and routine that I require.' ,
                          './images/DogImages/dog7.jfif');
INSERT INTO `Pet` VALUES ('D108', 'Rocky', 6, 'Male', 17000.0,
                          'I am a quiet, thoughtful boy who once he gets to know you,
                    will smooch and happily show you my tricks. I would do best in a
                    household with members over the age of 12 who are going to be
                    committed to helping me build my confidence and work on my social skills.' ,
                          './images/DogImages/dog8.jfif');
INSERT INTO `Pet` VALUES ('D109', 'Sky', 4, 'Female', 10000.0,
                          'I am a sporty dog who would be the perfect outgoing companion
                     for an active family. I would love a family that is over the age of
                     12 who are committed to training and building the structure and routine
                     that I need to succeed.' ,
                          './images/DogImages/dog9.jfif');
INSERT INTO `Pet` VALUES ('D110', 'Gary', 7, 'Male', 12000.0,
                          'I am either out running around in a yard or trying to grab
                     someone''s attention. I am not keen on being around other dogs
                     so I would need to be the only dog in the home.' ,
                          './images/DogImages/dog10.jfif');

INSERT INTO `Pet` VALUES ('C101', 'Ash', 5, 'Male', 1000.0,
                          'I am a quiet fellow that doesn\'t take up too much space.
                     I''d love to have a quieter roommate who will spoil me on occasion
                     with tasty treats and affection.' ,
                          './images/CatImages/cat1.jfif');
INSERT INTO `Pet` VALUES ('C102', 'Bandit', '1', 'Male', '1200',
                          'Though my name may be deceiving, the only thing I am looking to
                    steal is your heart. My handsome looks and kind personality are enough
                    to rope anyone in- as long as you''ve got a comfy home and don''t mind a
                    cat being in your business every now and then!' ,
                          './images/CatImages/cat2.jfif');
INSERT INTO `Pet` VALUES ('C103', 'Blake', 10, 'Female', 900.0,
                          'I am a calm, sweet little girl who would love nothing
                     more than a quiet home and patient loving family. I can
                     be initially timid upon first intro, but with time I''d be a
                     wonderful companion with a shining personality.' ,
                          './images/CatImages/cat3.jfif');
INSERT INTO `Pet` VALUES ('C104', 'Chance', 5, 'Male', 1000.0,
                          'I am an energetic and talkative cat who wants all of your
                     attention, so I need to be the only animal in the home. I
                     need an experienced, cat home that understands my energetic
                     needs and can teach me when it''s play time and when it''s not.
                      I would do best in a home with no small children and everyone in
                       the home is 8 years and older.' ,
                          './images/CatImages/cat4.jfif');
INSERT INTO `Pet` VALUES ('C105', 'Kitty', 7, 'Female', 1100.0,
                          'I am a precious little girl who knows what she''s looking for in
                     a family. I am calm, keep myself plenty entertained, and keep my space
                     tidy, what more could you ask for? Though I enjoy a pet or two, I am
                      also independent and enjoy my alone time as well.' ,
                          './images/CatImages/cat5.jfif');
INSERT INTO `Pet` VALUES ('C106', 'Lily', 5, 'Female', 1000.0,
                          'I am a sweet and sassy little kitty that knows what she wants in
                     life. Though a plush throne and towering castle would be ideal,
                      a comfy home and plenty of play time is enough to keep me happy
                       and engaged.' ,
                          './images/CatImages/cat6.jfif');
INSERT INTO `Pet` VALUES ('C107', 'Maozi', 1, 'Male', 1300.0,
                          'I am a cat who prefers lots of room to stretch his legs and explore.
                     Although I\'m unsure in new situations, once I settle in, I am social,
                     energetic and love to play. I would do best in a cat savvy home where
                     everyone is 16-years-old and up.' ,
                          './images/CatImages/cat7.jfif');
INSERT INTO `Pet` VALUES ('C108', 'Mia', 6, 'Female', 1100.0,
                          'I am a beautiful siamese kitty who''s got the complete package:
                    Charm, looks, and a clever purrsonality. I can be a little shy
                    going into the home, but would love a patient family to give me a
                    warm bed and plenty of love.' ,
                          './images/CatImages/cat8.jpg');
INSERT INTO `Pet` VALUES ('C109', 'Milan', 8, 'Female', 800.0,
                          'I would really like my own home to rule over. I would like a home
                     with kids 12+. I can get along with some other cats but more tolerate
                      them than like them. I prefer to be an only cat.
                       My owner must be experienced and understand how to read cat body language.
                        I love to sit next to people and hang out around them, but struggle
                         with petting overstimulation and will swat if you fail to read my body
                          language correctly and pet me too much. My owner must be patient,
                          savvy, respectful, and have a good sense of humor to let me
                          rule their home as I see fit.' ,
                          './images/CatImages/cat9.jpg');
INSERT INTO `Pet` VALUES ('C110', 'Oly', 5, 'Male', 1100.0,
                          'I am a calm kitty who''s just as sweet as he is handsome!
                    I can be initially timid meeting new people, but over time will blossom
                     into a social lovebug that warms the hearts of everyone I meet.' ,
                          './images/CatImages/cat10.jpg');