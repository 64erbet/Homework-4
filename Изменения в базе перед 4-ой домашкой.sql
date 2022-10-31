ALTER TABLE developers DROP COLUMN reportday;
	
ALTER TABLE developers ADD COLUMN salary NUMERIC;

UPDATE developers SET salary = 500.00 WHERE developerid = 1
UPDATE developers SET salary = 600.00 WHERE developerid = 2
UPDATE developers SET salary = 700.55 WHERE developerid = 3
UPDATE developers SET salary = 800.55 WHERE developerid = 4
UPDATE developers SET salary = 900.55 WHERE developerid = 5

ALTER TABLE projects DROP COLUMN workersAmount;

ALTER TABLE projects ADD COLUMN createDate DATE;
UPDATE projects SET createdate = to_date('01-01-2001', 'DD-MM-YYYY') WHERE projectid = 1
UPDATE projects SET createdate = to_date('01-01-2002', 'DD-MM-YYYY') WHERE projectid = 2
UPDATE projects SET createdate = to_date('01-01-2003', 'DD-MM-YYYY') WHERE projectid = 3
UPDATE projects SET createdate = to_date('01-01-2004', 'DD-MM-YYYY') WHERE projectid = 4
UPDATE projects SET createdate = to_date('01-01-2005', 'DD-MM-YYYY') WHERE projectid = 5

ALTER TABLE projects ADD COLUMN reportDate DATE;
UPDATE projects SET reportdate = to_date('01-01-2011', 'DD-MM-YYYY') WHERE projectid = 1
UPDATE projects SET reportdate = to_date('01-01-2012', 'DD-MM-YYYY') WHERE projectid = 2
UPDATE projects SET reportdate = to_date('01-01-2013', 'DD-MM-YYYY') WHERE projectid = 3
UPDATE projects SET reportdate = to_date('01-01-2014', 'DD-MM-YYYY') WHERE projectid = 4
UPDATE projects SET reportdate = to_date('01-01-2015', 'DD-MM-YYYY') WHERE projectid = 5
