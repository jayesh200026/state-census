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
	 * @return the number of data read from file
	 * Reads the csv file and maps to CsvStateCensus class.
	 * 
	 */
	public int readStateRecord() throws MyException {
		int count = 0;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
			@SuppressWarnings("unchecked")
			CsvToBean<CsvStateCensus> csvToBean = new CsvToBeanBuilder(reader).withType(CsvStateCensus.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			Iterator<CsvStateCensus> csvUserIterator = csvToBean.iterator();
			while (csvUserIterator.hasNext()) {
				CsvStateCensus state = csvUserIterator.next();
				count++;
//                if(state instanceof CsvStateCensus) {
//                	if(((CsvStateCensus)state).getState()==null || ((CsvStateCensus)state).getPopulation()==null || ((CsvStateCensus)state).getAreaInSqKm()==null) {
//                		
//                		throw new MyException(MyException.ExceptionType.INCORRECT_HEADER,"Header problem");
//                	}
//                }

				if (state.getState() == null || state.getPopulation() == 0 || state.getAreaInSqKm() == 0) {
					throw new MyException(MyException.ExceptionType.INCORRECT_HEADER, "Header doesn't match");
				}
			}
		}

		catch (NoSuchFileException e) {
			if (SAMPLE_CSV_FILE_PATH.contains(".csv")) {
				throw new MyException(MyException.ExceptionType.FILE_NOT_FOUND, "File not found");
			}
			throw new MyException(MyException.ExceptionType.INCORRECT_TYPE, "Incorrect Type");

		} catch (RuntimeException e) {

			throw new MyException(MyException.ExceptionType.DELEMETER_NOT_FOUND, "Incorrect Delimeter");
		} catch (IOException e) {
			System.out.println(e);
		}

		return count;
	}
}
