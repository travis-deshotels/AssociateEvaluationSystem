package com.revature.aes.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.aes.beans.Format;
import com.revature.aes.beans.Option;
import com.revature.aes.beans.Question;
import com.revature.aes.services.FormatService;
import com.revature.aes.services.QuestionService;

@Component
public class CSVParser {
	private static final String commaReplacement = "!__comma__!";
	private static final String TRUE_OR_FALSE = "True/False";
	private static final String MULTIPLE_CHOICE = "Multiple Choice";
	private static final String MULTIPLE_SELECT = "Multiple Select";
	
	private List<Format> formatList;
	
	private Map<String, Format> formats;
	@Autowired
	private QuestionService service;
	
	@Autowired
	private FormatService fmtService;
	
	/**
	 * Replace all instances of quoted commas with a placeholder.
	 * This will enable splitting the file by commas.
	 * @return List containing the cleaned input lines.
	 * @throws IOException 
	 */
	private List<String> escapeCommas(String filename) throws IOException, FileNotFoundException{
		List<String> linesCleaned = new ArrayList<>();
		String lineInFile = "";
		
		try(FileReader fr = new FileReader(filename); BufferedReader br = new BufferedReader(fr);) {
			while ((lineInFile = br.readLine()) != null) {
				// This will replace a quoted comma with the placeholder.
				// Any spaces inside the quotes are removed.
				lineInFile = lineInFile.replaceAll("\"\\s*,\\s*\"", commaReplacement);
				linesCleaned.add(lineInFile);
			}
		} 
		//TODO if (lineInFile.length()%2==1) throw InvalidFileFormatException
		return linesCleaned;
	}
	
	/**
	 * Replace the placeholders with commas.
	 * @param line may contain placeholders.
	 * @return Line with commas replacing the placeholders.
	 */
	private String placeCommas(String line){
		return line.replaceAll(commaReplacement, ",");
	}
	
	
	/**
	 * Get the format type id that matches the given question.
	 * @param numberOfChoices given by the user.
	 * @return the format type id needed for storing the question.
	 */
	private Format getFormatType(int numberOfChoices){		
		switch(numberOfChoices){
		case 0:
 			return formats.get(TRUE_OR_FALSE);
		case 1:
 			return formats.get(MULTIPLE_CHOICE);
		default:
 			return formats.get(MULTIPLE_SELECT);
		}
	}
	
	/**
	 * Parse a CSV file and create questions.
	 * @param filename contains the questions to be added to the system.
	 * @return all questions that are created from the input file.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public Map<String, Question> parseCSV(String filename) throws FileNotFoundException, IOException {
		String cvsSplitBy = ",";
		String[] linesList;
		String line;
		Question question;
		String questionText;
		Format format = null;
		Option option;
		boolean trueFalseQuestion = false;
		List<Option> options = new ArrayList<>();
		Map<String, Question> questions = new HashMap<>();
		
		this.formatList = fmtService.findAllFormats();
		
		formats = new HashMap<>();
		
		for(Format f : formatList){
			formats.put(f.getFormatName(), f);
		}
		
		// handle the quoted commas
		List<String> linesCleaned = null;
		linesCleaned = escapeCommas(filename);
		
		for (int i=0;i<linesCleaned.size();i+=2){
			// *question line
			line = linesCleaned.get(i);
			linesList = line.split(cvsSplitBy);
			questionText = placeCommas(linesList[0].trim());
			question = new Question();
			question.setQuestionText(questionText);
			
			for (int j=1;j<linesList.length;j++){
				option = new Option();
				option.setOptionText(placeCommas(linesList[j].trim()));
				option.setCorrect(0);
				option.setQuestion(question);
				options.add(option);
			}
			if(linesList.length==1){
				trueFalseQuestion = true;
			}
			// *answer line
			line = linesCleaned.get(i+1);
			linesList = line.split(cvsSplitBy);
			if (trueFalseQuestion){
				format = formats.get(TRUE_OR_FALSE);
				if("true".equalsIgnoreCase(linesList[0]) || "T".equalsIgnoreCase(linesList[0])){
					options.add(setTrueOrFalse("True", 1, question));
					options.add(setTrueOrFalse("False", 0, question));
				}
				else{
					options.add(setTrueOrFalse("True", 0, question));
					options.add(setTrueOrFalse("False", 1, question));
				}
			}
			else{
				// go through multiple choice options to mark the correct ones
				for (int j=0;j<linesList.length;j++){
					format = getFormatType(linesList.length);
					options = parseOptions(options, linesList[j]);
					}
				
			}
			question.setFormat(format);
			question.setMultiChoice(options);
			// save questions
			service.addQuestion(question);
			// add questions to the return map
			questions.put(question.getQuestionText(), question);
			// reset options
			options = new ArrayList<>();
		}
		return questions;
	}
	
	public List<Option> parseOptions(List<Option> options, String line){
		for (Option opt : options){
			// if answer is correct, update the option
			if (line.trim().equals(opt.getOptionText())){
				opt.setCorrect(1);
			}
		}
		return options;
	}
		
	public Option setTrueOrFalse(String isTrue,int isCorrect, Question question){
		return new Option(isTrue, isCorrect, question);
	}
	

}