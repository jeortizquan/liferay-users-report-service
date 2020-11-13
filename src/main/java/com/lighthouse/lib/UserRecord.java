package com.lighthouse.lib;

public class UserRecord {
    private String login;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String fullName;
    private String lastLogin;
    private String status;
    private String[] userGroups;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(String[] userGroups) {
        this.userGroups = userGroups;
    }

    public static final class UserRecordBuilder {
        private String login;
        private String firstName;
        private String lastName;
        private String emailAddress;
        private String fullName;
        private String lastLogin;
        private String status;
        private String[] userGroups;

        private UserRecordBuilder() {
        }

        public static UserRecordBuilder anUserRecord() {
            return new UserRecordBuilder();
        }

        public UserRecordBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserRecordBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserRecordBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserRecordBuilder emailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public UserRecordBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public UserRecordBuilder lastLogin(String lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public UserRecordBuilder status(String status) {
            this.status = status;
            return this;
        }

        public UserRecordBuilder userGroups(String[] userGroups) {
            this.userGroups = userGroups;
            return this;
        }

        public UserRecord build() {
            UserRecord userRecord = new UserRecord();
            userRecord.setLogin(login);
            userRecord.setFirstName(firstName);
            userRecord.setLastName(lastName);
            userRecord.setEmailAddress(emailAddress);
            userRecord.setFullName(fullName);
            userRecord.setLastLogin(lastLogin);
            userRecord.setStatus(status);
            userRecord.setUserGroups(userGroups);
            return userRecord;
        }
    }
}
