import React from 'react';
import i18n from 'i18next';
import { initReactI18next } from "react-i18next";

import en from "./locale/en.json";
import cs from "./locale/cs.json";

i18n
	// Make i18next available for all React's components.
	.use(initReactI18next)
	.init({
		resources: {
			en: {translation: en},
			cs: {translation: cs}
		},
		debug: false,
		lng: "cs", // If you're using a language detector, do not define the lng option.
		fallbackLng: "en",
		interpolation: { escapeValue: false }, // React already does escaping
	});


export default i18n;
