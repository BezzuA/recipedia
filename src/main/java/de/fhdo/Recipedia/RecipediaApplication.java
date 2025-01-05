package de.fhdo.Recipedia;

import de.fhdo.Recipedia.dto.ChallengeDto;
import de.fhdo.Recipedia.dto.CommentDto;
import de.fhdo.Recipedia.dto.DiscussionDto;
import de.fhdo.Recipedia.dto.RatingDto;
import de.fhdo.Recipedia.dto.RecipeDto;
import de.fhdo.Recipedia.dto.ReplyDto;
import de.fhdo.Recipedia.dto.UserDto;
import de.fhdo.Recipedia.entity.Recipe;
import de.fhdo.Recipedia.service.*;

import java.util.List;
import java.util.Date;
import java.util.Calendar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipediaApplication implements CommandLineRunner {
	private final UserService userService;
	private final RecipeService recipeService;
	private final CommentService commentService;
	private final RatingService ratingService;
	private final DiscussionService discussionService;
	private final ReplyService replyService;
	private final ChallengeService challengeService;

	public RecipediaApplication(UserService userService,
							   RecipeService recipeService,
							   CommentService commentService,
							   RatingService ratingService,
							   DiscussionService discussionService,
							   ReplyService replyService,
							   ChallengeService challengeService) {
		this.userService = userService;
		this.recipeService = recipeService;
		this.commentService = commentService;
		this.ratingService = ratingService;
		this.discussionService = discussionService;
		this.replyService = replyService;
		this.challengeService = challengeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(RecipediaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting service tests...");
		
		testUserService();
		testRecipeService();
		testCommentService();
		testRatingService();
		testDiscussionService();
		testReplyService();
		testChallengeService();
	}

	private void testUserService() {
		System.out.println("\nTesting UserService...");
		
		// Test registration
		UserDto newUser = userService.register("testuser", "password123");
		System.out.println("Created new user: " + newUser.getUsername());
		
		// Test login
		UserDto loggedInUser = userService.login("testuser", "password123");
		System.out.println("Logged in user: " + loggedInUser.getUsername());
		
		// Test update user
		loggedInUser.setEmail("test@example.com");
		loggedInUser.setBio("Test bio");
		UserDto updatedUser = userService.updateUser(loggedInUser);
		System.out.println("Updated user email: " + updatedUser.getEmail());
		
		// Test change password
		Boolean passwordChanged = userService.changePassword("testuser", "password123", "newpassword123");
		System.out.println("Password changed: " + passwordChanged);
		
		// Create a recipe for this user
		RecipeDto newRecipe = new RecipeDto();
		newRecipe.setTitle("User Test Recipe");
		newRecipe.setDescription("Testing user deletion with recipes");
		newRecipe.getAuthor().setUserId(newUser.getUserId());
		RecipeDto createdRecipe = recipeService.createRecipe(newRecipe);
		
		// Test delete user (should cascade delete their recipes)
		Boolean userDeleted = userService.deleteUser(newUser.getUserId());
		System.out.println("User deleted: " + userDeleted);
		
		System.out.println("UserService tests completed.");
	}

	private void testRecipeService() {
		System.out.println("\nTesting RecipeService...");
		
		// Create a test user first
		UserDto testUser = userService.register("chef_user", "chef123");
		
		// Create a recipe
		RecipeDto newRecipe = new RecipeDto();
		newRecipe.setTitle("Test Recipe");
		newRecipe.setDescription("A test recipe description");
		newRecipe.setCategory("Main Course");
		newRecipe.setIngredients(List.of("Ingredient 1", "Ingredient 2"));
		newRecipe.setInstructions("Test cooking instructions");
		newRecipe.getAuthor().setUserId(testUser.getUserId());
		
		RecipeDto createdRecipe = recipeService.createRecipe(newRecipe);
		System.out.println("Created new recipe: " + createdRecipe.getTitle());
		
		// Get recipe
		RecipeDto retrievedRecipe = recipeService.getRecipe(createdRecipe.getRecipeId(), testUser.getUserId());
		System.out.println("Retrieved recipe: " + retrievedRecipe.getTitle());
		
		// Update recipe
		retrievedRecipe.setDescription("Updated description");
		RecipeDto updatedRecipe = recipeService.updateRecipe(retrievedRecipe);
		System.out.println("Updated recipe description: " + updatedRecipe.getDescription());
		
		// Search recipes
		List<RecipeDto> searchResults = recipeService.getRecipes("Test");
		System.out.println("Found recipes by keyword: " + searchResults.size());
		
		// Get recipes by category
		List<RecipeDto> categoryRecipes = recipeService.getRecipesByCategory("Main Course");
		System.out.println("Found recipes by category: " + categoryRecipes.size());
		
		// Get recipes by author
		List<RecipeDto> authorRecipes = recipeService.getRecipesByAuthor(testUser.getUserId());
		System.out.println("Found recipes by author: " + authorRecipes.size());
		
		// Get most viewed recipes
		List<RecipeDto> mostViewedRecipes = recipeService.getMostViewedRecipes();
		System.out.println("Most viewed recipes count: " + mostViewedRecipes.size());
		
		// Delete recipe
		Boolean recipeDeleted = recipeService.deleteRecipe(createdRecipe.getRecipeId());
		System.out.println("Recipe deleted: " + recipeDeleted);
		
		System.out.println("RecipeService tests completed.");
	}

	private void testCommentService() {
		System.out.println("\nTesting CommentService...");
		
		// Create test users
		UserDto author = userService.register("comment_recipe_author", "pass123");
		UserDto commenter = userService.register("comment_user", "pass456");
		
		// Create a test recipe
		RecipeDto recipe = new RecipeDto();
		recipe.setTitle("Recipe for Comments");
		recipe.setDescription("A recipe to test comments");
		recipe.setCategory("Test Category");
		recipe.setIngredients(List.of("Test Ingredient"));
		recipe.setInstructions("Test Instructions");
		recipe.getAuthor().setUserId(author.getUserId());
		
		RecipeDto createdRecipe = recipeService.createRecipe(recipe);
		
		// Test adding comment
		CommentDto newComment = new CommentDto();
		newComment.setText("This is a test comment");
		newComment.getUser().setUserId(commenter.getUserId());
		newComment.setRecipeId(createdRecipe.getRecipeId());
		
		CommentDto createdComment = commentService.addComment(newComment);
		System.out.println("Created comment: " + createdComment.getText());
		
		// Test getting comments
		List<CommentDto> recipeComments = commentService.getCommentsByRecipe(createdRecipe.getRecipeId());
		System.out.println("Recipe comments count: " + recipeComments.size());
		
		// Test deleting comment
		Boolean commentDeleted = commentService.deleteComment(createdComment.getCommentId());
		System.out.println("Comment deleted: " + commentDeleted);
		
		System.out.println("CommentService tests completed.");
	}

	private void testRatingService() {
		System.out.println("\nTesting RatingService...");
		
		// Create test users
		UserDto author = userService.register("rating_recipe_author", "pass123");
		UserDto rater = userService.register("rating_user", "pass456");
		
		// Create a test recipe
		RecipeDto recipe = new RecipeDto();
		recipe.setTitle("Recipe for Ratings");
		recipe.setDescription("A recipe to test ratings");
		recipe.setCategory("Test Category");
		recipe.setIngredients(List.of("Test Ingredient"));
		recipe.setInstructions("Test Instructions");
		recipe.getAuthor().setUserId(author.getUserId());
		
		RecipeDto createdRecipe = recipeService.createRecipe(recipe);
		
		// Test adding rating
		RatingDto newRating = new RatingDto();
		newRating.setScore(5);
		newRating.setUserId(rater.getUserId());
		newRating.setRecipeId(createdRecipe.getRecipeId());
		
		RatingDto createdRating = ratingService.addRating(newRating);
		System.out.println("Created rating with score: " + createdRating.getScore());
		
		// Test getting average rating
		Double averageRating = ratingService.getAverageRating(createdRecipe.getRecipeId());
		System.out.println("Recipe average rating: " + averageRating);
		
		System.out.println("RatingService tests completed.");
	}

	private void testDiscussionService() {
		System.out.println("\nTesting DiscussionService...");
		
		// Create test user
		UserDto discussionCreator = userService.register("discussion_creator", "pass123");
		
		// Create discussion
		DiscussionDto newDiscussion = new DiscussionDto();
		newDiscussion.setTitle("Test Discussion");
		newDiscussion.setDescription("A test discussion about cooking");
		newDiscussion.getUser().setUserId(discussionCreator.getUserId());
		
		DiscussionDto createdDiscussion = discussionService.addDiscussion(newDiscussion);
		System.out.println("Created discussion: " + createdDiscussion.getTitle());
		
		// Get discussion
		DiscussionDto retrievedDiscussion = discussionService.getDiscussion(createdDiscussion.getDiscussionId());
		System.out.println("Retrieved discussion: " + retrievedDiscussion.getTitle());
		
		// Get all discussions
		List<DiscussionDto> allDiscussions = discussionService.getDiscussions();
		System.out.println("Total discussions: " + allDiscussions.size());
		
		// Get discussions by user
		List<DiscussionDto> userDiscussions = discussionService.getDiscussionsByUser(discussionCreator.getUserId());
		System.out.println("User's discussions count: " + userDiscussions.size());
		
		// Delete discussion
		Boolean discussionDeleted = discussionService.deleteDiscussion(createdDiscussion.getDiscussionId());
		System.out.println("Discussion deleted: " + discussionDeleted);
		
		System.out.println("DiscussionService tests completed.");
	}

	private void testReplyService() {
		System.out.println("\nTesting ReplyService...");
		
		// Create test users
		UserDto discussionCreator = userService.register("reply_discussion_creator", "pass123");
		UserDto replier = userService.register("replier_user", "pass456");
		
		// Create a discussion first
		DiscussionDto discussion = new DiscussionDto();
		discussion.setTitle("Discussion for Replies");
		discussion.setDescription("A discussion to test replies");
		discussion.getUser().setUserId(discussionCreator.getUserId());
		
		DiscussionDto createdDiscussion = discussionService.addDiscussion(discussion);
		
		// Create reply
		ReplyDto newReply = new ReplyDto();
		newReply.setText("This is a test reply");
		newReply.getUser().setUserId(replier.getUserId());
		newReply.setDiscussionId(createdDiscussion.getDiscussionId());
		
		ReplyDto createdReply = replyService.addReply(newReply);
		System.out.println("Created reply: " + createdReply.getText());
		
		// Get replies
		List<ReplyDto> discussionReplies = replyService.getRepliesByDiscussion(createdDiscussion.getDiscussionId());
		System.out.println("Discussion replies count: " + discussionReplies.size());
		
		// Delete reply
		Boolean replyDeleted = replyService.deleteReply(createdReply.getReplyId());
		System.out.println("Reply deleted: " + replyDeleted);
		
		// Clean up discussion
		discussionService.deleteDiscussion(createdDiscussion.getDiscussionId());
		
		System.out.println("ReplyService tests completed.");
	}

	private void testChallengeService() {
		System.out.println("\nTesting ChallengeService...");
		
		// Create test users
		UserDto participant = userService.register("challenge_participant", "pass123");
		
		// Create a challenge
		ChallengeDto newChallenge = new ChallengeDto();
		newChallenge.setTitle("Summer Recipe Challenge");
		newChallenge.setDescription("Create your best summer recipe");
		newChallenge.setStartDate(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		newChallenge.setEndDate(calendar.getTime());
		
		ChallengeDto createdChallenge = challengeService.addChallenge(newChallenge);
		System.out.println("Created challenge: " + createdChallenge.getTitle());
		
		// Create a recipe for the challenge
		RecipeDto challengeRecipe = new RecipeDto();
		challengeRecipe.setTitle("Summer Salad");
		challengeRecipe.setDescription("Perfect for the challenge");
		challengeRecipe.setCategory("Salad");
		challengeRecipe.setIngredients(List.of("Lettuce", "Tomatoes", "Cucumber"));
		challengeRecipe.setInstructions("Mix all ingredients");
		challengeRecipe.getAuthor().setUserId(participant.getUserId());
		
		RecipeDto createdRecipe = recipeService.createRecipe(challengeRecipe);
		
		// Join the challenge with the recipe
		ChallengeDto updatedChallenge = challengeService.joinChallenge(createdChallenge.getChallengeId(), 
																	  createdRecipe.getRecipeId());
		System.out.println("Joined challenge with recipe: " + createdRecipe.getTitle());
		
		// Get recipes by challenge
		List<RecipeDto> challengeRecipes = recipeService.getRecipesByChallenge(createdChallenge.getChallengeId());
		System.out.println("Found recipes in challenge: " + challengeRecipes.size());
		
		// Get challenge details
		ChallengeDto retrievedChallenge = challengeService.getChallenge(createdChallenge.getChallengeId());
		System.out.println("Retrieved challenge: " + retrievedChallenge.getTitle());
		
		// Get all challenges
		List<ChallengeDto> allChallenges = challengeService.getChallenges();
		System.out.println("Total challenges: " + allChallenges.size());
		
		// Get winner recipes (might be empty as challenge is ongoing)
		List<Recipe> winnerRecipes = challengeService.getWinnerRecipesByChallengeId(createdChallenge.getChallengeId());
		System.out.println("Winner recipes count: " + winnerRecipes.size());
		
		// Update challenge
		retrievedChallenge.setDescription("Updated challenge description");
		ChallengeDto updatedChallengeInfo = challengeService.updateChallenge(retrievedChallenge);
		System.out.println("Updated challenge description: " + updatedChallengeInfo.getDescription());
		
		// Clean up
		Boolean challengeDeleted = challengeService.deleteChallenge(createdChallenge.getChallengeId());
		System.out.println("Challenge deleted: " + challengeDeleted);
		
		// Get challenges by user
		List<ChallengeDto> userChallenges = challengeService.getChallengesByUserId(participant.getUserId());
		System.out.println("User's challenges count: " + userChallenges.size());
		
		System.out.println("ChallengeService tests completed.");
	}
}
