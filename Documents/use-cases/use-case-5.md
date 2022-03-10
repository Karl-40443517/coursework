# USE CASE: 5 Produce Multiple Country Reports

## CHARACTERISTIC INFORMATION

### Goal in Context

As someone analysing a large number of countries, I can run the same query multiple times
with a differing input to streamline the data retrieval process.

### Scope

Analysis

### Level

Primary task.

### Preconditions

World database includes country data

### Success End Condition

A report is produced for the user.

### Failed End Condition

No report is produced.

### Primary Actor

User

### Trigger

A request for Country report 

## MAIN SUCCESS SCENARIO

1. User requests Country report
2. User captures information from specified Country
3. User extracts Country data.
4. User is provided the Country report.

## EXTENSIONS

1**Country does not exist**:
1. User is informed that the country does not exist in the database.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.1