# USE CASE: 2. (Country Report) Top N Highest Populated Countries

## CHARACTERISTIC INFORMATION

### Goal in Context

As an organisation we need the top N populated countries for the following locations:

world, 
a continent, 
a region

### Scope

Organisation

### Level

Primary task.

### Preconditions

World database 

### Success End Condition

A report is produced for the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

User

### Trigger

A request for a Country Report

## MAIN SUCCESS SCENARIO

1. User requests Country Report
2. User captures information on the top N populated Countries
3. User extracts the relevant data 
4. User is provided the Country report for the organisation.

## EXTENSIONS

None

## SUB-VARIATIONS

Continent does not exist: User is informed that the continent does not exist in the database.

Region does not exist: User is informed that the Region does not exist in the database.

## SCHEDULE

**DUE DATE**: Release 1.1