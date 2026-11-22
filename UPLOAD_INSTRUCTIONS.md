# Missing files for jaswanthbogathi/StudentManagementSystemAJT

These files are ADDITIONS, not replacements for existing Java/JSP files.

## Upload from mobile
1. Extract this ZIP in your phone's Files app.
2. Open the GitHub repository and choose Add file > Upload files.
3. Upload files preserving their relative paths (database/ and .github/workflows/). GitHub browser upload may not preserve folders on some mobile browsers; if so, use Create new file and type the full path for each file.
4. Commit changes. Do not upload the ZIP alone expecting GitHub to extract it.

## Database
Execute database/schema.sql in MySQL 8, then optionally database/sample_data.sql.
No login user is seeded.

## Remaining work / cautions
Existing DBConnection.java has hardcoded localhost:3307/root/blank password, and existing UserDAO.java uses plaintext password comparison. These must be secured before any public deployment. A successful Maven build does not prove database integration or app runtime success. No live deployment has been performed.
