-- Users
INSERT INTO users (username, email, password, bio) VALUES
('billy_herrington', 'billy@gachi.com', 'password', 'Food enthusiast'),
('julia_child', 'julia@cooking.com', 'password123', 'Professional chef'),
('gordon_ramsay', 'gordon@hellskitchen.com', 'password456', 'Celebrity chef');

-- Challenges
INSERT INTO challenges (title, description, start_date, end_date) VALUES
('Summer Grilling', 'Create your best BBQ recipe', '2024-06-01', '2024-08-31'),
('Vegan Month', 'Share your favorite vegan dishes', '2024-07-01', '2024-07-31');

-- Recipes
INSERT INTO recipes (title, description, category, view_count, instructions, creation_date, user_id, challenge_id) VALUES
('Classic Burger', 'Juicy homemade burger', 'Main Course', 150, 'Mix ground beef with seasonings...', '2024-01-15', 1, 1),
('Vegan Curry', 'Spicy vegetable curry', 'Vegan', 120, 'Chop vegetables and cook with spices...', '2024-01-20', 2, 2);

-- Recipe ingredients (using @ElementCollection)
INSERT INTO recipe_ingredients (recipe_recipe_id, ingredients) VALUES
(1, 'Ground beef'),
(1, 'Salt'),
(1, 'Pepper'),
(2, 'Coconut milk'),
(2, 'Curry powder'),
(2, 'Vegetables');

-- Comments
INSERT INTO comments (text, creation_date, recipe_id, user_id) VALUES
('Great recipe!', '2024-01-16 10:30:00', 1, 2),
('Will try this weekend', '2024-01-21 15:45:00', 2, 3);

-- Ratings
INSERT INTO ratings (score, recipe_id, user_id) VALUES
(5, 1, 2),
(4, 2, 1);

-- Discussions
INSERT INTO discussions (title, description, creation_date, user_id) VALUES
('Best cooking techniques', 'Let''s share our favorite cooking methods', '2024-01-10', 1),
('Kitchen equipment recommendations', 'What are your must-have tools?', '2024-01-12', 2);

-- Replies
INSERT INTO replies (text, creation_date, user_id, discussion_id) VALUES
('I love slow cooking!', '2024-01-11', 2, 1),
('A good chef''s knife is essential', '2024-01-13', 3, 2);

-- User-Challenge relationships
INSERT INTO user_challenge (user_id, challenge_id) VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 2);

