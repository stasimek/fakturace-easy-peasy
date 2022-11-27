import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';

class Home extends Component {

	render() {
		const {t} = this.props;
		return (
			<div>
				<h2>Home</h2>
			</div>
		);
	}
}

export default withTranslation()(Home);
