/* Copyright 2002 The JA-SIG Collaborative.  All rights reserved.
*  See license distributed with this file and
*  available online at http://www.uportal.org/license.html
*/

package org.jasig.portal.layout.al.common;

import java.util.Enumeration;
import org.jasig.portal.PortalException;
import org.jasig.portal.layout.al.common.node.INode;
import org.jasig.portal.layout.al.common.node.INodeDescription;
import org.jasig.portal.layout.al.common.node.INodeId;
import org.jasig.portal.layout.al.common.node.NodeType;
import org.w3c.dom.Document;
import org.xml.sax.ContentHandler;

/**
 * An interface for abstracting operations performed on the user layout.
 *
 * @author Peter Kharchenko: pkharchenko at unicon.net
 * @author <a href="mailto:mvi@immagic.com">Michael Ivanov</a>
 * @version 1.1
 */
public interface ILayoutManager extends ILayoutManagerCommands {
    /**
     * Gets a user layout (with appropriate markings).
     *
     * @return the user layout
     * @exception PortalException if an error occurs
     */
    public IUserLayout getUserLayout() throws PortalException;

    /**
     * Sets a user layout (with appropriate markings).
     *
     * @param userLayout the user layout
     * @exception PortalException if an error occurs
     */
    public void setUserLayout(IUserLayout userLayout) throws PortalException;

    /**
     * Output user layout (with appropriate markings) into
     * a <code>ContentHandler</code>
     *
     * @param ch a <code>ContentHandler</code> value
     * @exception PortalException if an error occurs
     */
    public void getUserLayout(ContentHandler ch) throws PortalException;

    /**
     * Output subtree of a user layout (with appropriate markings) defined by a particular node into
     * a <code>ContentHandler</code>
     *
     * @param nodeId an id of a node determining a user layout subtree.
     * @param ch a <code>ContentHandler</code> value
     * @exception PortalException if an error occurs
     */
    public void getUserLayout(INodeId nodeId, ContentHandler ch) throws PortalException;

    /**
     * Set a user layout store implementation.
     *
     * @param ls an <code>IUserLayoutStore</code> value
     */
    //public void setLayoutStore(IUserLayoutStore ls);

    /**
     * Signal manager to load a user layout from a database
     *
     * @exception PortalException if an error occurs
     */
    //public void loadUserLayout() throws PortalException;

    /**
     * Signal manager to persist user layout to a database
     *
     * @exception PortalException if an error occurs
     */
    //public void saveUserLayout() throws PortalException;


    /**
     * Obtain a description of a node (channel or a folder) in a given user layout.
     *
     * @param nodeId a node id.
     * @return an <code>NodeDescription</code> value
     * @exception PortalException if an error occurs
     */
    public INodeDescription getNode(INodeId nodeId) throws PortalException;

    /**
     * Test if a particular node can be added at a given location.
     *
     * @param node an <code>INodeDescription</code> value describing the node to be added.
     * @param parentId an id of a parent to which the node to be added.
     * @param nextSiblingId an id of a sibling prior to which the node to be inserted. (<code>null</code> to append at the end)
     * @return a <code>boolean</code> value
     * @exception PortalException if an error occurs
     */
    public boolean canAddNode(INode node, INodeId parentId, INodeId nextSiblingId) throws PortalException;

    /**
     * Test if a particular node can be moved to a given location.
     *
     * @param nodeId an id of a node to be moved.
     * @param parentId an id of a parent to which the node to be moved.
     * @param nextSiblingId an id of a sibling prior to which the node is to be inserted (<code>null</code> to append at the end)
     * @return a <code>boolean</code> value
     * @exception PortalException if an error occurs
     */
    public boolean canMoveNode(INodeId nodeId, INodeId parentId, INodeId nextSiblingId) throws PortalException;

    /**
     * Tests if a particular node can be deleted.
     *
     * @param nodeId a node id.
     * @return a <code>boolean</code> value
     * @exception PortalException if an error occurs
     */
    public boolean canDeleteNode(INodeId nodeId) throws PortalException;

    /**
     * Test if a certain node can be updated.
     *
     * @param nodeId a node id
     * @param nodeDescription a <code>INodeDescription</code> with new node information.
     * @return a <code>boolean</code> value
     * @exception PortalException if an error occurs
     */
    public boolean canUpdateNode(INodeId nodeId, INodeDescription nodeDescription) throws PortalException;

    /**
     * Ask manager to output markings at the locations where a given node can be added.
     * The marks will appear next time <code>getUserLayout</code> method is called.
     *
     * @param node an <code>INode</code> value or <code>null</code> to stop outputting add markings.
     * @exception PortalException if an error occurs
     */
    public void markAddTargets(INode node) throws PortalException;


    /**
     * Ask manager to output markings at the locations where a given node can be moved.
     * The marks will appear next time <code>getUserLayout</code> method is called.
     *
     * @param nodeId a valid node id or <code>null</code> to stop outputting move markings.
     * @exception PortalException if an error occurs
     */
    public void markMoveTargets(INodeId nodeId) throws PortalException;

    /**
     * Returns an Id of a parent user layout node.
     * The user layout root node always has ID= {@link IUserLayout#ROOT_NODE_NAME}
     *
     * @param nodeId a valid node id
     * @return a node id
     * @exception PortalException if an error occurs
     */
    public INodeId getParentId(INodeId nodeId) throws PortalException;

    /**
     * Returns a list of child node Ids for a given node.
     *
     * @param nodeId a node id
     * @return a <code>List</code> of <code>INodeId</code> child node Ids.
     * @exception PortalException if an error occurs
     */
    public Enumeration getChildIds(INodeId nodeId) throws PortalException;

    /**
     * Determine an Id of a next sibling node.
     *
     * @param nodeId a node id
     * @return an id of a next sibling node, or <code>null</code> if this is the last sibling.
     * @exception PortalException if an error occurs
     */
    public INodeId getNextSiblingId(INodeId nodeId) throws PortalException;


    /**
     * Determine an Id of a previous sibling node.
     *
     * @param nodeId a node id
     * @return an id of a previous sibling node, or <code>null</code> if this is the first sibling.
     * @exception PortalException if an error occurs
     */
    public INodeId getPreviousSiblingId(INodeId nodeId) throws PortalException;

    /**
     * Return a cache key, uniqly corresponding to the composition and the structure of the user layout.
     *
     * @return a <code>String</code> value
     * @exception PortalException if an error occurs
     */
    public String getCacheKey() throws PortalException;


    /**
     * Register a layout event listener
     *
     * @param l a <code>LayoutEventListener</code> object
     * @return a <code>boolean</code> success status
     */
    public boolean addLayoutEventListener(LayoutEventListener l);


    /**
     * Remove a registered layout event listener.
     *
     * @param l a <code>LayoutEventListener</code> object
     * @return a <code>boolean</code> success status
     */
    public boolean removeLayoutEventListener(LayoutEventListener l);


    //  This method should be removed whenever it becomes possible
    public Document getUserLayoutDOM() throws PortalException;

    /**
     * Returns a layout Id associated with this manager/
     *
     * @return an <code>int</code> layout Id value;
     */
    public int getLayoutId();

    /**
     * Returns a subscription id using the supplied functional name.
     *
     * @param fname  the functional name to lookup
     * @return a node id
     */
    public INodeId getSubscribeId(String fname) throws PortalException;

    /**
     * Returns an id of the root folder.
     *
     * @return an id of the root node
     */
    public INodeId getRootFolderId();
    
	/**
		 * Returns the depth of a node in the layout tree.
		 *
		 * @param nodeId a node id
		 * @return a depth value
		 * @exception PortalException if an error occurs
		 */
	public int getDepth(INodeId nodeId) throws PortalException;

    /**
     * A factory method to create an empty <code>INodeDescription</code> instance
     *
     * @param nodeType a node type
     * @return an <code>INodeDescription</code> instance
     * @exception PortalException if the error occurs.
     */
    public INodeDescription createNodeDescription( NodeType nodeType ) throws PortalException;

}
