import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { Button, Form, FormGroup, Input, Label } from 'reactstrap';
import Options from '../component/html/Options';
import FormRow from '../component/html/FormRow';
import Cookies from 'js-cookie';
import Api from '../service/Api'

class Setting extends Component {

	constructor(props) {
		super(props);
		this.state = {item: {}};
		this.enums = {};
		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	async componentDidMount() {
		Api.getMany([
			'/api/user',
			'/api/enum/subject-legal-form',
			'/api/enum/country',
			'/api/enum/vat-type',
			'/api/enum/vat-period',
			'/api/enum/currency',
			'/api/enum/tax-office',
			'/api/enum/cz-nace',
		], ([user, legalForm, country, vatType, vatPeriod, currency, taxOffice, czNace]) => {
			this.setState({
				item: user
			});
			this.enums.legalForm = legalForm;
			this.enums.country = country;
			this.enums.vatType = vatType;
			this.enums.vatPeriod = vatPeriod;
			this.enums.currency = currency;
			this.enums.taxOffice = taxOffice;
			this.enums.czNace = czNace;
		});
	}

	handleChange(event) {
		const target = event.target;
		const value = target.value;
		const name = target.name;
		let item = this.state.item;
		item[name] = value;
		this.setState({item});
	}

	async handleSubmit(event) {
		event.preventDefault();
		const {item} = this.state;
		const {t} = this.props;
		await Api.call(
			'PUT',
			'/api/user/' + item.id,
			item,
			t('Settings saved.')
		);
	}

	render() {
		const {t} = this.props;
		const {item} = this.state;
		const divStyle = {width: '99%'};
		return (
			<div style={divStyle}>
				<h2>{t('Setting')}</h2>
				<Form onSubmit={this.handleSubmit}>
					<FormRow>
						<FormGroup>
							<Label for="login">{t('Username')}</Label>
							<Input type="text" id="login" value={item.login || ''} disabled/>
						</FormGroup>
						<FormGroup>
							<Label for="email">{t('Email')}</Label>
							<Input type="text" name="email" id="email" value={item.email || ''}
								   onChange={this.handleChange} autoComplete="email"/>
						</FormGroup>
						<FormGroup>
							<Label for="name">{t('Name')}</Label>
							<Input type="text" name="name" id="name" value={item.name || ''}
								   onChange={this.handleChange} autoComplete="name"/>
						</FormGroup>
					</FormRow>
					<FormRow>
						<FormGroup>
							<Label for="companyName">{t('Company name / name')}</Label>
							<Input type="text" name="companyName" id="companyName" value={item.companyName || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
						<FormGroup>
							<Label for="companyNumber">{t('Company ID number')}</Label>
							<Input type="text" name="companyNumber" id="companyNumber" value={item.companyNumber || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
						<FormGroup>
							<Label for="legalForm">{t('Legal form')}</Label>
							<Input type="select" name="legalForm" id="legalForm" value={item.legalForm || ''}
								   onChange={this.handleChange}>
								<Options map={this.enums.legalForm}/>
							</Input>
						</FormGroup>
						<FormGroup>
							<Label for="czNace">{t('Main business')}</Label>
							<Input type="select" name="czNace" id="czNace" value={item.czNace || ''}
								   onChange={this.handleChange}>
								<Options map={this.enums.czNace}/>
							</Input>
						</FormGroup>
					</FormRow>
					<FormRow>
						<FormGroup>
							<Label for="street">{t('Street')}</Label>
							<Input type="text" name="street" id="street" value={item.street || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
						<FormGroup>
							<Label for="city">{t('City')}</Label>
							<Input type="text" name="city" id="city" value={item.city || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
						<FormGroup>
							<Label for="zip">{t('ZIP code')}</Label>
							<Input type="text" name="zip" id="zip" value={item.zip || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
						<FormGroup>
							<Label for="country">{t('Country')}</Label>
							<Input type="select" name="country" id="country" value={item.country || ''}
								   onChange={this.handleChange}>
								<Options map={this.enums.country}/>
							</Input>
						</FormGroup>
					</FormRow>
					<FormRow>
						<FormGroup>
							<Label for="vatNumber">{t('VAT number')}</Label>
							<Input type="text" name="vatNumber" id="vatNumber" value={item.vatNumber || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
						<FormGroup>
							<Label for="vatType">{t('VAT type')}</Label>
							<Input type="select" name="vatType" id="vatType" value={item.vatType || ''}
								   onChange={this.handleChange}>
								<Options map={this.enums.vatType}/>
							</Input>
						</FormGroup>
						<FormGroup>
							<Label for="vatPeriod">{t('VAT period')}</Label>
							<Input type="select" name="vatPeriod" id="vatPeriod" value={item.vatPeriod || ''}
								   onChange={this.handleChange}>
								<Options map={this.enums.vatPeriod}/>
							</Input>
						</FormGroup>
						<FormGroup>
							<Label for="taxOffice">{t('Tax office')}</Label>
							<Input type="select" name="taxOffice" id="taxOffice" value={item.taxOffice || ''}
								   onChange={this.handleChange}>
								<Options map={this.enums.taxOffice}/>
							</Input>
						</FormGroup>
					</FormRow>
					<FormRow>
						<FormGroup>
							<Label for="currency">{t('Currency')}</Label>
							<Input type="select" name="currency" id="currency" value={item.currency || ''}
								   onChange={this.handleChange}>
								<Options map={this.enums.currency} nonEmpty/>
							</Input>
						</FormGroup>
						<FormGroup>
							<Label for="note">{t('Note')}</Label>
							<Input type="text" name="note" id="note" value={item.note || ''}
								   onChange={this.handleChange}
								   placeholder={t('Company registered in the trade register...')}/>
						</FormGroup>
						<FormGroup>
							<Label for="phone">{t('Phone')}</Label>
							<Input type="text" name="phone" id="phone" value={item.phone || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
					</FormRow>
					<FormRow>
						<FormGroup>
							<Label for="bankName">{t('Bank name')}</Label>
							<Input type="text" name="bankName" id="bankName" value={item.bankName || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
						<FormGroup>
							<Label for="bankAcountNumber">{t('Bank account number')}</Label>
							<Input type="text" name="bankAcountNumber" id="bankAcountNumber" value={item.bankAcountNumber || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
						<FormGroup>
							<Label for="iban">{t('IBAN')}</Label>
							<Input type="text" name="iban" id="iban" value={item.iban || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
						<FormGroup>
							<Label for="bic">{t('BIC (SWIFT)')}</Label>
							<Input type="text" name="bic" id="bic" value={item.bic || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
					</FormRow>
					<FormRow>
						<FormGroup>
							<Label for="defaultDuePeriod">{t('Default due period (days)')}</Label>
							<Input type="text" name="defaultDuePeriod" id="defaultDuePeriod" value={item.defaultDuePeriod || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
						<FormGroup>
							<Label for="defaultUnit">{t('Default unit')}</Label>
							<Input type="text" name="defaultUnit" id="defaultUnit" value={item.defaultUnit || ''}
								   onChange={this.handleChange} placeholder="ks, hod..."/>
						</FormGroup>
						<FormGroup>
							<Label for="defaultUnitPrice">{t('Default unit price')}</Label>
							<Input type="text" name="defaultUnitPrice" id="defaultUnitPrice" value={item.defaultUnitPrice || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
					</FormRow>
					<FormGroup>
						<Button color="primary" type="submit">{t('Save')}</Button>
					</FormGroup>
				</Form>
			</div>
		);
	}
}

export default withTranslation()(Setting);
