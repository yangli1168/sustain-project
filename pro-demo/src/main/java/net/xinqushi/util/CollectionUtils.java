package net.xinqushi.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CollectionUtils {
	public CollectionUtils() {
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Collection collection) {
		return collection == null || collection.isEmpty();
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Map map) {
		return map == null || map.isEmpty();
	}

	@SuppressWarnings("rawtypes")
	public static List arrayToList(Object source) {
		return Arrays.asList(ObjectUtils.toObjectArray(source));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void mergeArrayIntoCollection(Object array,
			Collection collection) {
		if (collection == null) {
			throw new IllegalArgumentException("Collection must not be null");
		} else {
			Object[] arr = ObjectUtils.toObjectArray(array);
			Object[] arr$ = arr;
			int len$ = arr.length;

			for (int i$ = 0; i$ < len$; ++i$) {
				Object elem = arr$[i$];
				collection.add(elem);
			}

		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void mergePropertiesIntoMap(Properties props, Map map) {
		if (map == null) {
			throw new IllegalArgumentException("Map must not be null");
		} else {
			String key;
			Object value;
			if (props != null) {
				for (Enumeration en = props.propertyNames(); en
						.hasMoreElements(); map.put(key, value)) {
					key = (String) en.nextElement();
					value = props.getProperty(key);
					if (value == null) {
						value = props.get(key);
					}
				}
			}

		}
	}

	@SuppressWarnings("rawtypes")
	public static boolean contains(Iterator iterator, Object element) {
		if (iterator != null) {
			while (iterator.hasNext()) {
				Object candidate = iterator.next();
				if (ObjectUtils.nullSafeEquals(candidate, element)) {
					return true;
				}
			}
		}

		return false;
	}

	@SuppressWarnings("rawtypes")
	public static boolean contains(Enumeration enumeration, Object element) {
		if (enumeration != null) {
			while (enumeration.hasMoreElements()) {
				Object candidate = enumeration.nextElement();
				if (ObjectUtils.nullSafeEquals(candidate, element)) {
					return true;
				}
			}
		}

		return false;
	}

	@SuppressWarnings("rawtypes")
	public static boolean containsInstance(Collection collection, Object element) {
		if (collection != null) {
			Iterator i$ = collection.iterator();

			while (i$.hasNext()) {
				Object candidate = i$.next();
				if (candidate == element) {
					return true;
				}
			}
		}

		return false;
	}

	@SuppressWarnings("rawtypes")
	public static boolean containsAny(Collection source, Collection candidates) {
		if (!isEmpty(source) && !isEmpty(candidates)) {
			Iterator i$ = candidates.iterator();

			Object candidate;
			do {
				if (!i$.hasNext()) {
					return false;
				}

				candidate = i$.next();
			} while (!source.contains(candidate));

			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	public static Object findFirstMatch(Collection source, Collection candidates) {
		if (!isEmpty(source) && !isEmpty(candidates)) {
			Iterator i$ = candidates.iterator();

			Object candidate;
			do {
				if (!i$.hasNext()) {
					return null;
				}

				candidate = i$.next();
			} while (!source.contains(candidate));

			return candidate;
		} else {
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T findValueOfType(Collection<?> collection, Class<T> type) {
		if (isEmpty(collection)) {
			return null;
		} else {
			Object value = null;
			Iterator i$ = collection.iterator();

			while (true) {
				Object element;
				do {
					if (!i$.hasNext()) {
						return (T) value;
					}

					element = i$.next();
				} while (type != null && !type.isInstance(element));

				if (value != null) {
					return null;
				}

				value = element;
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object findValueOfType(Collection<?> collection,
			Class<?>[] types) {
		if (!isEmpty(collection) && !ObjectUtils.isEmpty(types)) {
			Class[] arr$ = types;
			int len$ = types.length;

			for (int i$ = 0; i$ < len$; ++i$) {
				Class type = arr$[i$];
				Object value = findValueOfType(collection, type);
				if (value != null) {
					return value;
				}
			}

			return null;
		} else {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public static boolean hasUniqueObject(Collection collection) {
		if (isEmpty(collection)) {
			return false;
		} else {
			boolean hasCandidate = false;
			Object candidate = null;
			Iterator i$ = collection.iterator();

			while (i$.hasNext()) {
				Object elem = i$.next();
				if (!hasCandidate) {
					hasCandidate = true;
					candidate = elem;
				} else if (candidate != elem) {
					return false;
				}
			}

			return true;
		}
	}

	@SuppressWarnings("rawtypes")
	public static Class<?> findCommonElementType(Collection collection) {
		if (isEmpty(collection)) {
			return null;
		} else {
			Class candidate = null;
			Iterator i$ = collection.iterator();

			while (i$.hasNext()) {
				Object val = i$.next();
				if (val != null) {
					if (candidate == null) {
						candidate = val.getClass();
					} else if (candidate != val.getClass()) {
						return null;
					}
				}
			}

			return candidate;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <E> Iterator<E> toIterator(Enumeration<E> enumeration) {
		return new CollectionUtils.EnumerationIterator(enumeration);
	}

	private static class EnumerationIterator<E> implements Iterator<E> {
		private Enumeration<E> enumeration;

		public EnumerationIterator(Enumeration<E> enumeration) {
			this.enumeration = enumeration;
		}

		public boolean hasNext() {
			return this.enumeration.hasMoreElements();
		}

		public E next() {
			return this.enumeration.nextElement();
		}

		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Not supported");
		}
	}
}
