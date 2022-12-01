import React, { Component, Fragment } from 'react';
import { withTranslation } from 'react-i18next';

class Options extends Component {

	render() {
		if (!this.props.map) {
			return;
		}

		const {t} = this.props;
		const emptyOption = this.props.nonEmpty === undefined ? <option>{t('--')}</option> : '';
		return (
			<Fragment>
				{emptyOption}
				{Object.entries(this.props.map).map(v => <option value={v[0]}>{v[1]}</option>)}
			</Fragment>
		);
	}
}

export default withTranslation()(Options);
