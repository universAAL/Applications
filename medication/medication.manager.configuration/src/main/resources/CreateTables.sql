/*

ENUMS:

ROLE (PATIENT, CAREGIVER, PHYSICIAN)


UNIT_CLASS (PILLS, DROPS)


REFERENCE (INTAKE, REFILL)

STATUS (ACTIVE, INACTIVE)

MEAL_RELATION (BEFORE, WITH_MEAL, AFTER, ANY)

STATUS ("planned","actived","finished","cancelled", "prolonged")

*/

CREATE TABLE MEDICATION_MANAGER.PERSON (
  ID              INT NOT NULL,
  NAME            VARCHAR(77) NOT NULL,
  PERSON_URI      VARCHAR(144) NOT NULL,
  ROLE            VARCHAR(18) NOT NULL,
  USERNAME        VARCHAR(18),
  PASSWORD        VARCHAR(18),
  CAREGIVER_SMS   VARCHAR(18),
  CONSTRAINT PERSON_PK PRIMARY KEY (ID),
  CONSTRAINT PERSON_UNIQUE UNIQUE (PERSON_URI)
);

CREATE TABLE MEDICATION_MANAGER.DOCTOR_PATIENT (
  ID              INT NOT NULL,
  DOCTOR_FK_ID    INT NOT NULL,
  PATIENT_FK_ID   INT NOT NULL,
  CONSTRAINT DOCTOR_PATIENT PRIMARY KEY (ID),
  CONSTRAINT DOCTOR_PATIENT_PATIENT_FK FOREIGN KEY (PATIENT_FK_ID)
    REFERENCES MEDICATION_MANAGER.PERSON (ID),
  CONSTRAINT DOCTOR_PATIENT_DOCTOR_FK FOREIGN KEY (DOCTOR_FK_ID)
    REFERENCES MEDICATION_MANAGER.PERSON (ID)
);

CREATE TABLE MEDICATION_MANAGER.MEDICINE (
  ID              INT NOT NULL,
  MEDICINE_NAME   VARCHAR(77) NOT NULL,
  MEDICINE_INFO   CLOB,
  SIDE_EFFECTS    CLOB,
  INCOMPLIANCES   CLOB,
  MEAL_RELATION   VARCHAR(36) NOT NULL,
  CONSTRAINT MEDICINE_PK PRIMARY KEY (ID),
  CONSTRAINT MEDICINE_UNIQUE UNIQUE (MEDICINE_NAME)
);

CREATE TABLE MEDICATION_MANAGER.DISPENSER (
  ID                      INT NOT NULL,
  PATIENT_FK_ID           INT NOT NULL,
  DISPENSER_URI           VARCHAR(144) NOT NULL,
  INSTRUCTIONS_FILE_NAME  VARCHAR(144) NOT NULL,
  CONSTRAINT DISPENSER_PK PRIMARY KEY (ID),
  CONSTRAINT DISPENSER_PATIENT_FK FOREIGN KEY (PATIENT_FK_ID)
    REFERENCES MEDICATION_MANAGER.PERSON (ID)
);

CREATE TABLE MEDICATION_MANAGER.MEDICINE_INVENTORY (
  ID                      INT NOT NULL,
  PATIENT_FK_ID           INT NOT NULL,
  MEDICINE_FK_ID          INT NOT NULL,
  UNIT_CLASS              VARCHAR(666) NOT NULL,
  QUANTITY                INT NOT NULL,
  WARNING_THRESHOLD       INT NOT NULL,
  CONSTRAINT MEDICINE_INVENTORY_PK PRIMARY KEY (ID),
  CONSTRAINT MEDICINE_INVENTORY_PATIENT_FK FOREIGN KEY (PATIENT_FK_ID)
    REFERENCES MEDICATION_MANAGER.PERSON (ID),
  CONSTRAINT MEDICINE_INVENTORY_MEDICINE_FK FOREIGN KEY (MEDICINE_FK_ID)
    REFERENCES MEDICATION_MANAGER.MEDICINE (ID)
);

CREATE TABLE MEDICATION_MANAGER.INVENTORY_LOG (
  ID                      INT NOT NULL,
  TIME_OF_CREATION        TIMESTAMP NOT NULL,
  PATIENT_FK_ID           INT NOT NULL,
  MEDICINE_FK_ID          INT NOT NULL,
  CHANGE_QUANTITY         INT NOT NULL,
  UNITS                   VARCHAR(6) NOT NULL,
  REFERENCE               VARCHAR(12) NOT NULL,
  CONSTRAINT INVENTORY_LOG_PK PRIMARY KEY (ID),
  CONSTRAINT INVENTORY_LOG_PATIENT_FK FOREIGN KEY (PATIENT_FK_ID)
    REFERENCES MEDICATION_MANAGER.PERSON (ID),
  CONSTRAINT INVENTORY_LOG_MEDICINE_FK FOREIGN KEY (MEDICINE_FK_ID)
    REFERENCES MEDICATION_MANAGER.MEDICINE (ID)
);

CREATE TABLE MEDICATION_MANAGER.PRESCRIPTION (
  ID                      INT NOT NULL,
  TIME_OF_CREATION        TIMESTAMP NOT NULL,
  PATIENT_FK_ID           INT NOT NULL,
  PHYSICIAN_FK_ID         INT NOT NULL,
  DESCRIPTION             VARCHAR(100) NOT NULL,
  STATUS                  VARCHAR(10) NOT NULL,
  CONSTRAINT PRESCRIPTION_PK PRIMARY KEY (ID),
  CONSTRAINT PRESCRIPTION_PATIENT_FK FOREIGN KEY (PATIENT_FK_ID)
    REFERENCES MEDICATION_MANAGER.PERSON (ID),
  CONSTRAINT PRESCRIPTION_PHYSICIAN_FK FOREIGN KEY (PHYSICIAN_FK_ID)
    REFERENCES MEDICATION_MANAGER.PERSON (ID)
);

CREATE TABLE MEDICATION_MANAGER.TREATMENT (
  ID                      INT NOT NULL,
  PRESCRIPTION_FK_ID      INT NOT NULL,
  MEDICINE_FK_ID          INT NOT NULL,
  START_DATE              TIMESTAMP NOT NULL,
  END_DATE                TIMESTAMP NOT NULL,
  STATUS                  VARCHAR(10) NOT NULL,
  CONSTRAINT TREATMENT_PK PRIMARY KEY (ID),
  CONSTRAINT TREATMENT_PRESCRIPTION_FK FOREIGN KEY (PRESCRIPTION_FK_ID)
    REFERENCES MEDICATION_MANAGER.PRESCRIPTION (ID),
  CONSTRAINT TREATMENT_MEDICINE_FK FOREIGN KEY (MEDICINE_FK_ID)
    REFERENCES MEDICATION_MANAGER.MEDICINE (ID)
);

CREATE TABLE MEDICATION_MANAGER.INTAKE (
  ID              INT NOT NULL,
  TREATMENT_FK_ID INT NOT NULL,
  QUANTITY        INT NOT NULL,
  UNITS           VARCHAR(6) NOT NULL,
  TIME_PLAN       TIMESTAMP NOT NULL,
  TIME_TAKEN      TIMESTAMP,
  DISPENSER_FK_ID INT,
  CONSTRAINT INTAKE_PK PRIMARY KEY (ID),
  CONSTRAINT INTAKE_TREATMENT_FK FOREIGN KEY (TREATMENT_FK_ID)
    REFERENCES MEDICATION_MANAGER.TREATMENT (ID),
  CONSTRAINT DISPENSER_FK FOREIGN KEY (DISPENSER_FK_ID)
    REFERENCES MEDICATION_MANAGER.DISPENSER (ID)
);
