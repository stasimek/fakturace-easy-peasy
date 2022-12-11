import { useParams } from 'react-router-dom';

/**
 *  This is the crazy way how to inject URL parameters to class component.
 */
export default function withParams(Component) {
	return props => <Component {...props} params={useParams()}/>;
}
