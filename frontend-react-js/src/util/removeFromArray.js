/**
 *  Remove item from array using index.
 */
export default function removeFromArray(array, indexForRemoval) {
	if (indexForRemoval >= 0 && indexForRemoval < array.length) {
		array.splice(indexForRemoval, 1);
	}
}
