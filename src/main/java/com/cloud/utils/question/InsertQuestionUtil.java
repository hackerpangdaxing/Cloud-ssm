package com.cloud.utils.question;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 导入Excel
 * 
 * @author jiawenhao
 *
 */

public class InsertQuestionUtil {

	/** 总行数 */

	private int totalRows = 0;

	/** 总列数 */

	private int totalCells = 0;


	/**
	 * 
	 * @描述：得到总行数
	 * 
	 * @参数：@return
	 * 
	 * @返回值：int
	 */

	public int getTotalRows() {

		return totalRows;

	}

	/**
	 * 
	 * @描述：得到总列数
	 * @参数：@return
	 * 
	 * @返回值：int
	 */

	public int getTotalCells() {

		return totalCells;

	}

	/**
	 * 
	 * @描述：得到错误信息
	 * 
	 * @参数：@return
	 * 
	 * @返回值：String
	 */




	/**
	 * 
	 * @描述：根据文件名读取excel文件
	 * 
	 * @参数：@param filePath 文件完整路径
	 * 
	 * @参数：@return
	 * 
	 * @返回值：List
	 */

	public List<List<String>> read(String filePath, InputStream in) {
		// 相当于一个二维数组
		List<List<String>> dataLst = new ArrayList<List<String>>();

		try {
				boolean isExcel2003 = filePath.endsWith(".xls");
				dataLst = read(in, isExcel2003);
				in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		/** 返回最后读取的结果 */

		return dataLst;

	}

	/**
	 * 
	 * @描述：根据流读取Excel文件
	 * 
	 * @参数：@param inputStream
	 * 
	 * @参数：@param isExcel2003
	 * 
	 * @参数：@return
	 * 
	 * @返回值：List
	 */

	public List<List<String>> read(InputStream inputStream, boolean isExcel2003) {

		List<List<String>> dataLst = null;

		try {
			/** 根据版本选择创建Workbook的方式 */
			Workbook wb = null;

			if (isExcel2003) {
				wb = new HSSFWorkbook(inputStream);
			} else {
				wb = new XSSFWorkbook(inputStream);
			}
			dataLst = read(wb);

		} catch (IOException e) {
			e.printStackTrace();

		}

		return dataLst;

	}

	/**
	 * 
	 * @描述：读取数据
	 * 
	 * 
	 * @参数：@return
	 * 
	 * @返回值：List<List<String>>
	 */

	private List<List<String>> read(Workbook wb) {

		List<List<String>> dataLst = new ArrayList<List<String>>();
		/** 得到第一个shell */

		Sheet sheet = wb.getSheetAt(0);

		/** 得到Excel的行数 包括空行 */

		// this.totalRows = sheet.getPhysicalNumberOfRows();
		this.totalRows = sheet.getLastRowNum();
		/** 得到Excel的列数 */
		if (this.totalRows >= 1 && sheet.getRow(0) != null) {

			// this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
			this.totalCells = sheet.getRow(0).getLastCellNum();

		}

		/** 循环Excel的行 */

		for (int r = 1; r < this.totalRows; r++) {

			Row row = sheet.getRow(r);

			if (row == null) {

				continue;

			}

			List<String> rowLst = new ArrayList<String>();

			/** 循环Excel的列 */

			for (int c = 0; c < this.getTotalCells(); c++) {
				Cell cell = row.getCell(c);
				String cellValue = "";
				if(c == 0){
					cellValue = "1";
				}else if (null != cell) {
					// 以下是判断数据的类型
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cellValue = cell.getStringCellValue();
				}
				if("".equals(cellValue) || cellValue.trim() == null){
					cellValue = "";
				}
				rowLst.add(cellValue);

			}
			/** 保存第r行 */

			dataLst.add(rowLst);

		}
		return dataLst;

	}
}
