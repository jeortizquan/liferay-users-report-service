package com.lighthouse.lib;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GenerateUserList {
    public XSSFWorkbook processResponse(String response) throws IOException {

        JSONObject responseJson = new JSONObject(response);
        JSONArray records = responseJson.getJSONArray("records");
        JSONArray ugrecords = responseJson.getJSONArray("usergroups");
        String[] userGroupRecords = new String[ugrecords.length()];
        for (int ug = 0; ug < ugrecords.length(); ug++) {
            userGroupRecords[ug] = ugrecords.getString(ug);
        }
        UserRecord[] gebruikerRecords = new UserRecord[records.length()];
        for (int i = 0; i < records.length(); i++) {
            if (records.getJSONObject(i) != null) {
                JSONObject record = records.getJSONObject(i);
                JSONArray arrayUserGroups = record.getJSONArray("userGroups");
                String[] userGroups = new String[arrayUserGroups.length()];
                for (int j = 0; j < arrayUserGroups.length(); j++) {
                    userGroups[j] = arrayUserGroups.getString(j);
                }
                gebruikerRecords[i] = UserRecord.UserRecordBuilder.anUserRecord()
                        .login(record.getString("login"))
                        .firstName(record.getString("firstName"))
                        .lastName(record.getString("lastName"))
                        .emailAddress(record.getString("emailAddress"))
                        .fullName(record.getString("fullName"))
                        .status(record.getString("status"))
                        .lastLogin(record.getString("lastLogin"))
                        .userGroups(userGroups)
                        .build();

            }

        }
        return writeXlsx(gebruikerRecords, userGroupRecords);
    }

    private Date getJSONDate(JSONObject jsonObject, String propertyName) {
        try {
            return jsonObject.get(propertyName) == null ? null : new Date(jsonObject.getLong(propertyName));
        } catch (Exception e) {
            return null;
        }
    }

    public XSSFWorkbook writeXlsx(UserRecord[] gebruikerRecords, String[] userGroupRecords) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("User records");
        Map<String, Object[]> dataContent = new HashMap<>();
        int primaryKey = 1;
        for (UserRecord u : gebruikerRecords) {
            dataContent.put(String.valueOf(primaryKey), new Object[]{primaryKey,
                    u.getLogin(),
                    u.getFirstName(),
                    u.getLastName(),
                    u.getEmailAddress(),
                    u.getFullName(),
                    u.getStatus(),
                    u.getLastLogin(),
                    u.getUserGroups()});
            primaryKey++;
        }

        Set<String> newRows = dataContent.keySet();
        createHeaderRow(sheet, userGroupRecords);
        int rownum = sheet.getLastRowNum();
        rownum = rownum == -1 ? 0 : 1;

        for (String key : newRows) {
            // Creating a new Row in existing XLSX sheet
            Row row = sheet.createRow(rownum++);
            Object[] objArr = dataContent.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof String[]) {
                    String[] values = (String[]) obj;

                    if (values.length > 0) {
                        for (int col = 1; col < userGroupRecords.length; col++) {
                            Cell acell = row.createCell(cellnum++);
                            acell.setCellValue("");
                        }
                        for (int ele = 0; ele < values.length; ele++) {
                            int pos = getIndexOf(userGroupRecords, values[ele]);
                            if (pos >= 0)  {
                                Cell acell = row.getCell(8 + pos);
                                acell.setCellValue("x");
                            }
                        }
                    }
                } else if (obj instanceof Boolean) {
                    cell.setCellValue((Boolean) obj);
                } else if (obj instanceof Long) {
                    cell.setCellValue((Long) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                } else if (obj instanceof Date) {
                    CreationHelper creationHelper = workbook.getCreationHelper();
                    CellStyle cellStyle = workbook.createCellStyle();
                    cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/mm/yyyy"));
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue((Date) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                }
            }
        }
        for(int col =0; col< 8+userGroupRecords.length; col++) {
            sheet.autoSizeColumn(col);
        }
        return workbook;
    }

    private int getIndexOf(String[] strings, String item) {
        for (int i = 0; i < strings.length; i++) {
            if (item.equals(strings[i])) return i;
        }
        return -1;
    }

    private void createHeaderRow(XSSFSheet sheet, String[] userGroupRecords) {

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);

        Row row = sheet.createRow(0);

        Cell cell1 = row.createCell(0);
        cell1.setCellStyle(cellStyle);
        cell1.setCellValue("No");

        Cell cell2 = row.createCell(1);
        cell2.setCellStyle(cellStyle);
        cell2.setCellValue("Login");

        Cell cell3 = row.createCell(2);
        cell3.setCellStyle(cellStyle);
        cell3.setCellValue("FirstName");

        Cell cell4 = row.createCell(3);
        cell4.setCellStyle(cellStyle);
        cell4.setCellValue("LastName");

        Cell cell5 = row.createCell(4);
        cell5.setCellStyle(cellStyle);
        cell5.setCellValue("Emailaddress");

        Cell cell6 = row.createCell(5);
        cell6.setCellStyle(cellStyle);
        cell6.setCellValue("FullName");

        Cell cell7 = row.createCell(6);
        cell7.setCellStyle(cellStyle);
        cell7.setCellValue("Status");

        Cell cell8 = row.createCell(7);
        cell8.setCellStyle(cellStyle);
        cell8.setCellValue("Last Login");

        int cl = 8;
        CellStyle cellStyle2 = sheet.getWorkbook().createCellStyle();
        Font font2 = sheet.getWorkbook().createFont();
        font2.setBold(true);
        font2.setFontHeightInPoints((short) 12);
        cellStyle2.setFont(font);
        cellStyle2.setRotation((short)90);

        for (String value : userGroupRecords) {
            Cell cells = row.createCell(cl++);
            cells.setCellStyle(cellStyle2);
            cells.setCellValue(value);
        }
    }
}
