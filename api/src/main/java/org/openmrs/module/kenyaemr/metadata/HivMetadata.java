/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.kenyaemr.metadata;

import org.openmrs.PatientIdentifierType.LocationBehavior;
import org.openmrs.module.kenyacore.metadata.bundle.AbstractMetadataBundle;
import org.openmrs.module.kenyacore.metadata.bundle.Requires;
import org.openmrs.module.kenyaemr.Dictionary;
import org.springframework.stereotype.Component;

import static org.openmrs.module.kenyacore.metadata.bundle.Constructors.*;

/**
 * HIV metadata bundle
 */
@Component
@Requires({ CommonMetadata.class })
public class HivMetadata extends AbstractMetadataBundle {

	public static final class EncounterType {
		public static final String HIV_CONSULTATION = "a0034eee-1940-4e35-847f-97537a35d05e";
		public static final String HIV_DISCONTINUATION = "2bdada65-4c72-4a48-8730-859890e25cee";
		public static final String HIV_ENROLLMENT = "de78a6be-bfc5-4634-adc3-5f1a280455cc";
	}

	public static final class Form {
		public static final String CLINICAL_ENCOUNTER_HIV_ADDENDUM = "bd598114-4ef4-47b1-a746-a616180ccfc0";
		public static final String FAMILY_HISTORY = "7efa0ee0-6617-4cd7-8310-9f95dfee7a82";
		public static final String HIV_DISCONTINUATION = "e3237ede-fa70-451f-9e6c-0908bc39f8b9";
		public static final String HIV_ENROLLMENT = "e4b506c1-7379-42b6-a374-284469cba8da";
		public static final String MOH_257_FACE_PAGE = "47814d87-2e53-45b1-8d05-ac2e944db64c";
		public static final String MOH_257_VISIT_SUMMARY = "23b4ebbd-29ad-455e-be0e-04aa6bc30798";
	}

	public static final class PatientIdentifierType {
		public static final String UNIQUE_PATIENT_NUMBER = "05ee9cf4-7242-4a17-b4d4-00f707265c8a";
	}

	public static final class Program {
		public static final String HIV = "dfdc6d40-2f2f-463d-ba90-cc97350441a8";
	}

	/**
	 * @see org.openmrs.module.kenyacore.metadata.bundle.AbstractMetadataBundle#install()
	 */
	@Override
	public void install() {
		install(encounterType("HIV Enrollment", "Enrollment onto HIV program", EncounterType.HIV_ENROLLMENT));
		install(encounterType("HIV Consultation", "Collection of HIV-specific data during the main consultation", EncounterType.HIV_CONSULTATION));
		install(encounterType("HIV Discontinuation", "Discontinuation from HIV program", EncounterType.HIV_DISCONTINUATION));

		install(form("HIV Enrollment", null, EncounterType.HIV_ENROLLMENT, "1", Form.HIV_ENROLLMENT));
		install(form("Clinical Encounter - HIV addendum", null, EncounterType.HIV_CONSULTATION, "1", Form.CLINICAL_ENCOUNTER_HIV_ADDENDUM));
		install(form("Family History", null, CommonMetadata.EncounterType.REGISTRATION, "1", Form.FAMILY_HISTORY));
		install(form("MOH 257 Face Page", null, EncounterType.HIV_CONSULTATION, "1", Form.MOH_257_FACE_PAGE));
		install(form("MOH 257 Visit Summary", null, EncounterType.HIV_CONSULTATION, "1", Form.MOH_257_VISIT_SUMMARY));
		install(form("HIV Discontinuation", null, EncounterType.HIV_DISCONTINUATION, "1", Form.HIV_DISCONTINUATION));

		install(patientIdentifierType("Unique Patient Number", "Assigned to every HIV patient", "\\d+", "Facility code followed by sequential number",
				null, LocationBehavior.NOT_USED, false, PatientIdentifierType.UNIQUE_PATIENT_NUMBER));

		install(program("HIV Program", "Treatment for HIV-positive patients", Dictionary.HIV_PROGRAM, Program.HIV));
	}
}