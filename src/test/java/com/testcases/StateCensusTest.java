package com.testcases;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.census.CsvStateCensus;
import com.census.MyException;
import com.census.StateCensusAnalyser;

public class StateCensusTest {

	/**
	 *  This test case pass when a file with 8 rows is read.
	 */
	@Test
	public void givenCsvFile_with8rowscomparingwith8_returnstrue() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/jayeshkumar/learning_path/BATCH51/git_part/statecensusanalysis/data/StateCensusData.csv");
			Assert.assertEquals(8, analyser.readStateRecord());
		} catch (MyException e) {
			System.out.println(e);
		}
	}

	@Test
	public void givenCsvFile_whichIsIncorrect_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/jayeshkumar/learning_path/BATCH51/git_part/statecensusanalysis/data/StateCensusData1.csv");
			analyser.readStateRecord();
		} catch (MyException e) {
			Assert.assertEquals("File not found", e.getMessage());
			System.out.println(e);
		}

	}

	@Test
	public void givenCsvFile_whichIsWrongType_returnsFalse() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/jayeshkumar/learning_path/BATCH51/git_part/statecensusanalysis/data/book1.txt");
			analyser.readStateRecord();
		} catch (MyException e) {
			Assert.assertEquals("Incorrect Type", e.getMessage());
			System.out.println(e);
		}
	}

	@Test
	public void givenCsvFile_withwrongdelimeter_returnsFalse() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/jayeshkumar/learning_path/BATCH51/git_part/statecensusanalysis/data/InvalidDelimeter.csv");
			analyser.readStateRecord();
		} catch (MyException e) {

			System.out.println(e);
			Assert.assertEquals("Incorrect Delimeter", e.getMessage());
		}
	}

	@Test
	public void givenCsvFile_withwrongdHeader_returnsFalse() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/jayeshkumar/learning_path/BATCH51/git_part/statecensusanalysis/data/IncorrectHeader.csv");
			analyser.readStateRecord();
		} catch (MyException e) {

			System.out.println(e);
			Assert.assertEquals("Header doesn't match", e.getMessage());
		}
	}

}
