import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { Button, Form, FormGroup, Input, Label } from 'reactstrap';
import Options from '../component/html/Options';
import FormRow from '../component/html/FormRow';
import withParams from '../util/withParams'
import Api from '../service/Api'

class Subject extends Component {

	constructor(props) {
		super(props);
		this.state = {item: {}};
		this.enums = {};
		this.originalCompanyName = '';
		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	async componentDidMount() {
		const isNew = this.props.params.id === 'new';
		const urls = [
			'/api/enum/subject-type',
			'/api/enum/subject-legal-form',
			'/api/enum/country',
			'/api/enum/currency',
			!isNew ? '/api/subject/' + this.props.params.id : null
		];
		const thenFunction = ([type, legalForm, country, currency, subject]) => {
			this.enums.type = type;
			this.enums.legalForm = legalForm;
			this.enums.country = country;
			this.enums.currency = currency;
			this.setState({
				item: subject || {}
			});
			this.originalCompanyName = !isNew ? subject.companyName : null;
		};
		Api.getMany(urls, thenFunction);
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
			(item.id) ? 'PUT' : 'POST',
			'/api/subject' + (item.id ? '/' + item.id : ''),
			item, t('Subject saved.')
		);
		this.props.history.push('/subjects');
	}

	render() {
		const {t} = this.props;
		const {item} = this.state;
		const isNew = this.props.params.id === 'new';
		const divStyle = {width: '99%'};
		return (
			<div style={divStyle}>
				<h2>{isNew ? t('New subject') : this.originalCompanyName}</h2>
				<Form onSubmit={this.handleSubmit}>
					<FormRow>
						<FormGroup>
							<Label for="type">{t('Type')}</Label>
							<Input type="select" name="type" id="type" value={item.type || ''}
								   onChange={this.handleChange}>
								<Options map={this.enums.type}/>
							</Input>
						</FormGroup>
					</FormRow>
					<FormRow>
						<FormGroup>
							<Label for="legalForm">{t('Legal form')}</Label>
							<Input type="select" name="legalForm" id="legalForm" value={item.legalForm || ''}
								   onChange={this.handleChange}>
								<Options map={this.enums.legalForm}/>
							</Input>
						</FormGroup>
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
							<Label for="vatNumber">{t('VAT number')}</Label>
							<Input type="text" name="vatNumber" id="vatNumber" value={item.vatNumber || ''}
								   onChange={this.handleChange}/>
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
							<Label for="defaultDuePeriod">{t('Default due period (days)')}</Label>
							<Input type="text" name="defaultDuePeriod" id="defaultDuePeriod"
								   value={item.defaultDuePeriod || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
						<FormGroup>
							<Label for="defaultCurrency">{t('Default currency')}</Label>
							<Input type="select" name="defaultCurrency" id="defaultCurrency"
								   value={item.defaultCurrency || ''}
								   onChange={this.handleChange}>
								<Options map={this.enums.currency}/>
							</Input>
						</FormGroup>
						<FormGroup>
							<Label for="defaultUnitPrice">{t('Default unit price')}</Label>
							<Input type="text" name="defaultUnitPrice" id="defaultUnitPrice"
								   value={item.defaultUnitPrice || ''}
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

export default withTranslation()(withParams(Subject));
