import React, { Component } from 'react';
import { Col, Row } from 'reactstrap';

/**
 * Will render <Row> with <Col>s for all children elements.
 *
 * Usage:
 *     <FormRow>
 *         <FormGroup md="3" sm="6">
 *         </FormGroup>
 *         <FormGroup md="9" sm="6">
 *         </FormGroup>
 *     </FormRow>
 *
 * Defaults:
 *     - md="3"
 *     - sm="6"
 */
export default class FormRow extends Component {

	render() {
		return (
				<Row {...this.props}>
					{React.Children.map(
							this.props.children,
							child => {
								const md = child.props.md || 3;
								const sm = child.props.sm || 6;
								return <Col md={md} sm={sm}>{child}</Col>;
							}
					)}
				</Row>
		);
	}
}
