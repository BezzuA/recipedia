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
public class RecipediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipediaApplication.class, args);
	}

}
