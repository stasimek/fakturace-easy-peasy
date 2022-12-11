import React, { Component, Fragment } from 'react';
import { withTranslation } from 'react-i18next';

/**
 * Render <option>s using parameter "map" or "list" as a source.
 * 
 * Usage:
 *     <Options map={object}/>
 *     or
 *     <Options list={listOfObjects} key="keyAttribute" value="valueAttribute"/>
 *
 * Optional attributes:
 *     nonEmpty - Don't render empty option.
 */
class Options extends Component {

	render() {
		if (!this.props.map && !(this.props.list && this.props.key && this.props.value)) {
			return;
		}

		const {t} = this.props;
		const emptyOption = this.props.nonEmpty === undefined ? <option>{t('--')}</option> : '';
		if (this.props.map) {
			return (
				<Fragment>
					{emptyOption}
					{Object.entries(this.props.map).map(v => <option value={v[0]}>{v[1]}</option>)}
				</Fragment>
			)
		} else {
			return (
				<Fragment>
					{emptyOption}
					{this.props.list.map(
							v => <option value={v[this.props.key]}>{v[this.props.value]}</option>
					)}
				</Fragment>
			)
		}

	}
}

export default withTranslation()(Options);
