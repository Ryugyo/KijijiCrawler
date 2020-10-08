# cscc01 -- assignment 3

## information

- **graded by**: Kevin Zhang
- **email:** mstr.zhang@mail.utoronto.ca
- **remark policy:** consult email within 4 days of receiving mark

---

## grade overview

**TOTAL MARK**: 38/40

|component|mark|total|
|---|---|---|
|frontend|13|15|
|backend|20|20|
|structure|5|5|

**additional deductions**:

- none

**BONUS**:

- none

---

## frontend

|component|mark|total|
|---|---|---|
|frontend has required components|10|10|
|frontend has good user experience|1|3|
|frontend style is acceptable|2|2|

**additional comments**:

- has all the required components
- some bugs with user experience
    - sometimes after running a crawl, the google chart doesn't seem to appear
    - sometimes after running a crawl, none of the table data appears
- styling is not great but acceptable
    - table extends past the viewport
    - looks kind of ugly but at least an attempt was made

---

## backend

|component|mark|total|
|---|---|---|
|crawler works|10|10|
|some sort of database structure exists|1|1|
|database structure is adequate|2|2|
|database has some pre-populated entries|2|2|
|unit tests exist and are of good quality|2|5|

**additional comments**:

- crawler seems to work fine for valid urls
- database looks good
- tests exist but are not very robust
    - not good coverage; just basic asserts
    - not using mockito anywhere
    - do not seem to be setup properly (running `mvn test` does not actually run the tests)

---

## structure

|component|mark|total|
|---|---|---|
|using some sort of MVC architecture|3|3|
|no design flaws apparent|2|2|

**additional comments**:

- structure looks good
