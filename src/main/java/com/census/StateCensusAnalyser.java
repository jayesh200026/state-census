package com.census;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
	private String SAMPLE_CSV_FILE_PATH = "";

	public StateCensusAnalyser() {

	}

	public StateCensusAnalyser(String SAMPLE_CSV_FILE_PATH) {
		this.SAMPLE_CSV_FILE_PATH = SAMPLE_CSV_FILE_PATH;
	}

	/**
	 * 
	 * @param className= Name of the class to which we wanna map csv file data to.
	 * @return number of data read from csv file
	 * 
	 */
	public <T> int readStateRecord(Class<T> className) throws MyException {
		int count = 0;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
			@SuppressWarnings("unchecked")
			CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader).withType(className).withIgnoreLeadingWhiteSpace(true)
					.build();
			Iterator<T> csvUserIterator = csvToBean.iterator();
			while (csvUserIterator.hasNext()) {
				T state = csvUserIterator.next();
				count++;
				if (state instanceof CsvStateCensus) { // comparing if read file is of CsvStateCensus class
					if (((CsvStateCensus) state).getState() == null || ((CsvStateCensus) state).getPopulation() == 0
							|| ((CsvStateCensus) state).getAreaInSqKm() == 0) {

						throw new MyException(MyException.ExceptionType.INCORRECT_HEADER, "Header doesn't match");
					}
				}

				if (state instanceof CsvStateCode) { // comparing if read file is of CsvStateCode class
					if (((CsvStateCode) state).getSrNo() == 0 || ((CsvStateCode) state).getStateName() == null
							|| ((CsvStateCode) state).getStateCode() == null) {

						throw new MyException(MyException.ExceptionType.INCORRECT_HEADER, "Header doesn't match");
					}
				}

			}
		}

		catch (NoSuchFileException e) {
			if (SAMPLE_CSV_FILE_PATH.contains(".csv")) {
				throw new MyException(MyException.ExceptionType.FILE_NOT_FOUND, "File not found");
			}
			throw new MyException(MyException.ExceptionType.INCORRECT_TYPE, "Incorrect File Type");

		} catch (RuntimeException e) {

			throw new MyException(MyException.ExceptionType.DELIMITER_NOT_FOUND, "Incorrect Delimiter");
		} catch (IOException e) {
			System.out.println(e);
		}

		return count;
	}
}
