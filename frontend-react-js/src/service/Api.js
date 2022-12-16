import Cookies from 'js-cookie';
import { toast } from 'react-toastify';
import i18n from '../util/i18n';

export default class Api {

	static call(method, url, body, successMessage) {
		return fetch(url, {
			method: method,
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json',
				'X-XSRF-TOKEN': Cookies.get('XSRF-TOKEN')
			},
			body: JSON.stringify(body),
		}).then((response) => {
			if (response.ok) {
				if(response.headers.get("Content-Type") === 'application/json') {
					return response.json();
				} else {
					return response.text();
				}
			}
			return Promise.reject(response);
		}).then((response) => {
			toast.success(successMessage);
		}).catch((response) => {
			response.json().then((json) => {
				toast.error(
					json.message || i18n.t('Something went wrong.'),
					{autoClose: false, closeOnClick: false}
				);
			});
		});
	}

	static getMany(urls, thenFunction) {
		const fetches = [];
		urls.forEach((url) => url ? fetches.push(fetch(url)) : '');
		Promise.all(fetches)
			.then((responses) => {
				const responseBodies = [];
				for (let response of responses) {
					if (!response.ok) {
						return Promise.reject(response);
					}
					if(response.headers.get("Content-Type") === 'application/json') {
						responseBodies.push(response.json());
					} else {
						responseBodies.push(response.text());
					}
				}
				return Promise.all(responseBodies);
			})
			.then(thenFunction)
			.catch((response) => {
				response.json().then((json) => {
					toast.error(
						json.message || i18n.t('Something went wrong.'),
						{autoClose: false, closeOnClick: false}
					);
				});
			});
	}
}
