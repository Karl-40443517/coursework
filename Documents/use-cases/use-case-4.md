# USE CASE: 4. (City Report) Top N Highest Populated Cities

## CHARACTERISTIC INFORMATION

### Goal in Context

As an organisation we need the top N populated cities for the following locations:

world, a continent, a region, a Country, a District

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

A request for a City Report

## MAIN SUCCESS SCENARIO

1. User requests City Report
2. User captures information on the top N populated Cities
3. User extracts the relevant data 
4. User is provided the City report for the organisation.

## EXTENSIONS

None

## SUB-VARIATIONS

Continent does not exist: User is informed that the continent does not exist in the database.

Region does not exist: User is informed that the Region does not exist in the database.

Country does not exist: User is informed that the Country does not exist in the database.

District does not exist: User is informed that the District does not exist in the database.

## SCHEDULE

**DUE DATE**: Release 1.1