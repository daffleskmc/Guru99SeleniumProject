This is a Selenium practice from Guru99 website.

Given info:
URL = http://www.demo.guru99.com/V4/
Username mngr267020
Password qerEnas

Date started 19-June
Note: This access is valid only for 20 days.

---------------------------
Test Scenario: Verify the login selection
Test Case:
    ss1: Enter valid user and password
    ss2: Enter invalid userid and valid password
    ss3: Enter valid userid and invalid password
    ss4: Enter invalid userid and invalid password
Test Steps:
    1. Go to http://www.demo.guru99.com/V4/
    2. Enter valid userid
    3. Enter valid password
    4. Click login
    5. Verify Manager ID shown in output
    6. Take screenshot
Test Data
    userid
    password
Expected Result
    Login successful
