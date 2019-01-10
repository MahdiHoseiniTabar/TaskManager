package com.example.caspian.taskmanager.database;

public class TaskDbSchema {
    public static final String NAME = "TaskDb";
    public static final int VERSION = 1;

    public class Account {
        public static final String NAME = "AccountTable";

        public class AccountCols{
            public static final String UUID = "AccountUUID";
            public static final String USERNAME = "UserName";
            public static final String PASSWORD = "Password";

        }
    }

    public class Task {
        public static final String NAME = "TaskTable";
        public class TaskCols{
            public static final String UUID = "TaskUUID";
            public static final String TITLE = "Title";
            public static final String DESCRIPTION = "Description";
            public static final String DATE = "Date";
            public static final String ISDONE = "IsDone";
            public static final String ACCOUNTID = "ID";
        }
    }
}
