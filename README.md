# Data-Structures-for-Efficient-Storage-of-Vehicle-Locations
Vehicle hiring companies such as Uber, rely on fast localization of available vehicles near potential
customers. Such efficient localization requires specialized data structures, and the goal of this project is to implement
and use one such data structure.

Vehicles and customers are localized using GPS devices available in mobile phones, and these devices determine
the longitude and latitude coordinates as floating-point numbers. To simplify the task, however, the
geographical area under consideration will be divided into small cells and integers will be used to identify each location on the map.
so a city is divided into small cells so that a location is identified by two integer coordinates x and y. Note
that a single cell may contain several vehicles and customers. When a customer request a ride, only vehicles within a certain range of this customer will be contacted.

To store locations for efficient search, a tree structure similar to a BST is used. In addition to data, each node of
this tree contains a location (u, v) and has up to four children:
 The nodes in the sub-tree rooted at Child 1 contain locations (x, y) satisfying: x < u and y ≤ v.
 The nodes in the sub-tree rooted at Child 2 contain locations (x, y) satisfying: x ≤ u and y > v.
 The nodes in the sub-tree rooted at Child 3 contain locations (x, y) satisfying: x > u and y ≥ v.
 The nodes in the sub-tree rooted at Child 4 contain locations (x, y) satisfying: x ≥ u and y < v.


The classes Location and Pair used to represent locations and pairs of elements respectively.
The class VehicleHiringManager, which stores the locations of vehicles and allows to add, remove and move vehicles
to a new position. Vehicles are identified by a key of type String. An important functionality of this class is
finding all vehicles within a square centered at the customer’s location.
The class LinkedList used to store data when needed.
The class BST used to map vehicle IDs to their locations.
The class TreeLocator, used to store the vehicles available at every location.
The class TreeLocatorMap is used to store the locations of vehicles by combining BST and TreeLocator to
guarantee that vehicle IDs are unique (unlike the class TreeLocator, which only guarantees the uniqueness of the
locations but not the data).

