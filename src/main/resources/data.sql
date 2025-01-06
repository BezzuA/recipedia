-- Users
INSERT INTO users (username, email, password, bio) VALUES
('billy_herrington', 'billy@gachi.com', 'password', 'Food enthusiast'),
('julia_child', 'julia@cooking.com', 'password123', 'Professional chef'),
('gordon_ramsay', 'gordon@hellskitchen.com', 'password456', 'Celebrity chef'),
('jamie_oliver', 'jamie@food.com', 'password789', 'British chef and restaurateur'),
('emeril_lagasse', 'emeril@bam.com', 'password101', 'Celebrity chef and TV personality'),
('ina_garten', 'ina@barefoot.com', 'password202', 'Cookbook author');

-- Challenges
INSERT INTO challenges (title, description, start_date, end_date) VALUES
('Summer Grilling', 'Create your best BBQ recipe', '2024-06-01', '2024-08-31'),
('Vegan Month', 'Share your favorite vegan dishes', '2024-07-01', '2024-07-31'),
('Holiday Baking', 'Create festive holiday treats', '2024-12-01', '2024-12-31'),
('Healthy January', 'Start the year with healthy recipes', '2024-01-01', '2024-01-31'),
('Spring Fresh', 'Cook with spring ingredients', '2024-03-01', '2024-03-31');

-- Recipes
INSERT INTO recipes (title, description, category, view_count, instructions, creation_date, user_id, challenge_id) VALUES
('Classic Burger', 'Juicy homemade burger', 'Main Course', 150, 'Mix ground beef with seasonings...', '2024-01-15', 1, 1),
('Vegan Curry', 'Spicy vegetable curry', 'Vegan', 120, 'Chop vegetables and cook with spices...', '2024-01-20', 2, 2),
('Spaghetti Carbonara', 'Classic Italian pasta dish', 'Italian', 200, 'Cook pasta, prepare sauce with eggs and cheese...', '2024-01-25', 2, NULL),
('Thai Green Curry', 'Spicy and aromatic curry', 'Asian', 180, 'Prepare curry paste, cook with coconut milk...', '2024-01-28', 3, NULL),
('French Onion Soup', 'Traditional French soup', 'Soup', 160, 'Caramelize onions, add beef broth...', '2024-02-01', 2, NULL),
('Summer Salad', 'Fresh and light salad', 'Salad', 90, 'Mix fresh vegetables with vinaigrette...', '2024-02-05', 1, 1),
('Beef Wellington', 'Classic British dish', 'Main Course', 250, 'Prepare beef tenderloin, wrap in pastry...', '2024-02-10', 4, NULL),
('Seafood Gumbo', 'New Orleans classic', 'Soup', 175, 'Make roux, add seafood and seasonings...', '2024-02-12', 5, NULL),
('Perfect Roast Chicken', 'Sunday dinner favorite', 'Main Course', 300, 'Season chicken, roast with vegetables...', '2024-02-15', 6, NULL),
('Chocolate Soufflé', 'Elegant dessert', 'Dessert', 220, 'Prepare chocolate base, whip eggs...', '2024-02-18', 4, 3),
('Green Smoothie Bowl', 'Healthy breakfast', 'Breakfast', 150, 'Blend fruits and greens...', '2024-02-20', 5, 4),
('Spring Risotto', 'Creamy rice dish', 'Italian', 180, 'Cook arborio rice with spring vegetables...', '2024-02-22', 6, 5);

-- Recipe ingredients (using @ElementCollection)
INSERT INTO recipe_ingredients (recipe_recipe_id, ingredients) VALUES
(1, 'Ground beef'),
(1, 'Salt'),
(1, 'Pepper'),
(2, 'Coconut milk'),
(2, 'Curry powder'),
(2, 'Vegetables'),
(3, 'Spaghetti'),
(3, 'Eggs'),
(3, 'Pecorino Romano'),
(3, 'Guanciale'),
(4, 'Green Curry Paste'),
(4, 'Coconut Milk'),
(4, 'Thai Basil'),
(5, 'Onions'),
(5, 'Beef Broth'),
(5, 'Gruyere Cheese'),
(6, 'Mixed Greens'),
(6, 'Cherry Tomatoes'),
(6, 'Cucumber'),
(7, 'Beef Tenderloin'),
(7, 'Puff Pastry'),
(7, 'Mushroom Duxelles'),
(8, 'Shrimp'),
(8, 'Andouille Sausage'),
(8, 'Okra'),
(9, 'Whole Chicken'),
(9, 'Fresh Herbs'),
(9, 'Root Vegetables'),
(10, 'Dark Chocolate'),
(10, 'Eggs'),
(10, 'Sugar'),
(11, 'Spinach'),
(11, 'Banana'),
(11, 'Chia Seeds'),
(12, 'Arborio Rice'),
(12, 'Asparagus'),
(12, 'Peas');

-- Comments
INSERT INTO comments (text, creation_time, recipe_id, user_id) VALUES
('Great recipe!', '2024-01-16 10:30:00', 1, 2),
('Will try this weekend', '2024-01-21 15:45:00', 2, 3),
('Perfect pasta dish!', '2024-01-26 11:15:00', 3, 3),
('Love the authentic flavors', '2024-01-29 12:00:00', 4, 1),
('Best onion soup ever', '2024-02-02 13:30:00', 5, 2),
('So fresh and healthy', '2024-02-06 14:15:00', 6, 3),
('Impressive dish!', '2024-02-11 15:00:00', 7, 2),
('Authentic flavors', '2024-02-13 15:30:00', 8, 3),
('Perfect Sunday meal', '2024-02-16 16:00:00', 9, 1),
('Restaurant quality', '2024-02-19 16:30:00', 10, 5),
('So nutritious', '2024-02-21 17:00:00', 11, 6),
('Creamy perfection', '2024-02-23 17:30:00', 12, 4),
('Made this for my family, they loved it!', '2024-02-12 10:00:00', 1, 4),
('Great weeknight dinner option', '2024-02-13 10:30:00', 2, 5),
('The flavors are perfectly balanced', '2024-02-14 11:00:00', 3, 6),
('Added extra garlic, turned out amazing', '2024-02-15 11:30:00', 4, 1),
('This recipe is foolproof', '2024-02-16 12:00:00', 5, 2),
('My new favorite recipe', '2024-02-17 12:30:00', 6, 3),
('The instructions were very clear', '2024-02-18 13:00:00', 7, 4),
('Perfect for special occasions', '2024-02-19 13:30:00', 8, 5),
('Will definitely make again', '2024-02-20 14:00:00', 9, 6),
('Such an elegant dessert', '2024-02-21 14:30:00', 10, 1),
('Love how healthy this is', '2024-02-22 15:00:00', 11, 2),
('Restaurant quality at home', '2024-02-23 15:30:00', 12, 3);

-- Ratings
INSERT INTO ratings (score, recipe_id, user_id) VALUES
(5, 1, 2),
(4, 2, 1),
(5, 3, 3),
(4, 4, 1),
(5, 5, 2),
(4, 6, 3),
(5, 7, 2),
(4, 8, 3),
(5, 9, 1),
(5, 10, 5),
(4, 11, 6),
(5, 12, 4),
(4, 3, 4),
(5, 3, 5),
(4, 3, 6),
(5, 3, 2),
(5, 9, 2),
(4, 9, 3),
(5, 9, 4),
(5, 9, 5),
(5, 10, 2),
(4, 10, 3),
(5, 10, 4),
(4, 10, 6);

-- Discussions
INSERT INTO discussions (title, description, creation_date, user_id) VALUES
('Best cooking techniques', 'Let''s share our favorite cooking methods', '2024-01-10', 1),
('Kitchen equipment recommendations', 'What are your must-have tools?', '2024-01-12', 2),
('Italian cooking tips', 'Share your secrets for perfect Italian dishes', '2024-01-20', 2),
('Asian cuisine basics', 'Getting started with Asian cooking', '2024-01-22', 3),
('Seasonal ingredients', 'Best ingredients for each season', '2024-01-24', 1),
('Baking tips and tricks', 'Share your baking secrets', '2024-02-01', 4),
('Meal prep ideas', 'Weekly meal planning strategies', '2024-02-05', 5),
('Wine pairing', 'Best wines for different dishes', '2024-02-08', 6),
('Kitchen gadgets', 'Essential tools for cooking', '2024-02-15', 4),
('Food photography', 'Tips for better food photos', '2024-02-20', 5);

-- Replies
INSERT INTO replies (text, creation_time, user_id, discussion_id) VALUES
('I love slow cooking!', '2024-01-11 09:15:00', 2, 1),
('A good chef''s knife is essential', '2024-01-13 14:30:00', 3, 2),
('Fresh ingredients are key', '2024-01-21 10:00:00', 1, 3),
('Start with basic stir-frying', '2024-01-23 10:30:00', 2, 4),
('Spring vegetables are coming!', '2024-01-25 11:00:00', 3, 5),
('Room temperature ingredients are key', '2024-02-02 11:30:00', 6, 6),
('Glass containers work best', '2024-02-06 12:00:00', 4, 7),
('Red wine with beef dishes', '2024-02-09 12:30:00', 5, 8),
('Love my instant pot', '2024-02-16 13:00:00', 6, 9),
('Natural lighting is best', '2024-02-21 13:30:00', 4, 10),
('I always use cast iron pans', '2024-02-03 09:00:00', 1, 1),
('Digital thermometer is essential', '2024-02-04 09:30:00', 2, 2),
('Learning knife skills changed everything', '2024-02-05 10:00:00', 3, 2),
('Mise en place is so important', '2024-02-06 10:30:00', 4, 3),
('Fresh herbs make a huge difference', '2024-02-07 11:00:00', 5, 3),
('Always toast your spices', '2024-02-08 11:30:00', 6, 4),
('Homemade stock is worth the effort', '2024-02-09 12:00:00', 1, 4),
('Meal prep saves so much time', '2024-02-10 12:30:00', 2, 7),
('Vacuum sealer is a game changer', '2024-02-11 13:00:00', 3, 7),
('Love cooking with seasonal produce', '2024-02-12 13:30:00', 4, 5),
('White wine pairs well with fish', '2024-02-13 14:00:00', 5, 8),
('Ring light helps with food photos', '2024-02-14 14:30:00', 6, 10),
('Silicone mats are great for baking', '2024-02-15 15:00:00', 1, 6),
('Pressure cooker speeds things up', '2024-02-16 15:30:00', 2, 9),
('Food styling makes a big difference', '2024-02-17 16:00:00', 3, 10);

-- User-Challenge relationships
INSERT INTO user_challenge (user_id, challenge_id) VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 2),
(1, 2),
(3, 1),
(2, 2),
(4, 3),
(5, 4),
(6, 5),
(4, 4),
(5, 5),
(6, 3);

