import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { Button, ButtonGroup, Table } from 'reactstrap';
import { Link } from 'react-router-dom';

class Subjects extends Component {

	constructor(props) {
		super(props);
		this.state = {subjects: []};
		this.remove = this.remove.bind(this);
	}

	componentDidMount() {
		fetch('/api/subjects')
				.then(response => response.json())
				.then(data => this.setState({subjects: data}));
	}

	async remove(id) {
		await fetch(`/api/subject/${id}`, {
			method: 'DELETE',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			}
		}).then(() => {
			let updatedSubjects = [...this.state.subjects].filter(i => i.id !== id);
			this.setState({subjects: updatedSubjects});
		});
	}

	render() {
		const {t} = this.props;
		const {subjects, isLoading} = this.state;

		if (isLoading) {
			return <p>Loading...</p>;
		}

		const subjectList = subjects.map(subject => {
			return (
				<tr key={subject.id}>
					<td style={{whiteSpace: 'nowrap'}}>{subject.companyName}</td>
					<td>
						<ButtonGroup>
							<Button size="sm" color="primary" tag={Link} to={"/subject/" + subject.id}>{t('Edit')}</Button>
							<Button size="sm" color="danger" onClick={() => this.remove(subject.id)}>{t('Delete')}</Button>
						</ButtonGroup>
					</td>
				</tr>
			);
		});

		return (
			<div>
				<div className="float-right">
					<Button color="success" tag={Link} to="/subject/new">{t('Add new subject')}</Button>
				</div>
				<h2>{t('Subjects')}</h2>
				<Table className="mt-4">
					<thead>
						<tr>
							<th width="60%">{t('Company name')}</th>
							<th width="40%">{t('Actions')}</th>
						</tr>
					</thead>
					<tbody>
						{subjectList}
					</tbody>
				</Table>
			</div>
		);
	}
}

export default withTranslation()(Subjects);
