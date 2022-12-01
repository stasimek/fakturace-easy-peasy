import React, { Component } from 'react';
import { Col, Row } from 'reactstrap';

export default class FormRow extends Component {

	render() {
		return (
			<Row>
				{React.Children.map(this.props.children, child => <Col md="3" sm="6">{child}</Col>)}
			</Row>
		);
	}
}
