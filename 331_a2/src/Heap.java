import java.util.ArrayList;

//---------------------------------------------
//				Heap Class
//---------------------------------------------
public class Heap {
	
	//---------------------------------------------
	//			Node inner Class
	//---------------------------------------------
	public class Node
	{
		//---------------------------------------------
		// 			Variable Declaration
		//---------------------------------------------
		private int m_iWeight;
		private String m_sValue;
		
		
		public Node(int i_iWeight, String i_sValue)
		{
			m_iWeight = i_iWeight;
			m_sValue = i_sValue;
		}
		
		public int GetWeight()
		{
			return m_iWeight;
		}
		
		public String GetValue()
		{
			return m_sValue;	
		}
	
	}
	
	//---------------------------------------------
	//			Variable Declaration
	//---------------------------------------------
	private ArrayList<Node> m_NodeDA;
	
	public Heap()
	{
		m_NodeDA = new ArrayList<Node>();
	}
	
	public void PrintHeapContent()
	{
		for(int i = 0; i < m_NodeDA.size(); ++i)
		{
			System.out.println("Index:" + i + "\t\tWeight:" + m_NodeDA.get(i).GetWeight() + "\t\tValue:" + m_NodeDA.get(i).GetValue());
		}
		
	}
	
	
	public void AddElement(int i_iWeight, String i_sValue)
	{
		m_NodeDA.add(new Node(i_iWeight,i_sValue));
		UpwardValidity(m_NodeDA.size() - 1);
	}
	
	public int GetSize()
	{
		return m_NodeDA.size();
	}
	
	public Node GetMaxNode()
	{
		if(m_NodeDA.size() > 0)
			return m_NodeDA.get(0);
		else
			return null;
	}
	
	public Node RemoveMaxNode()
	{
		if(m_NodeDA.size() > 0)
		{
			Node l_ReturnNode = m_NodeDA.get(0);
			//Store the last element into the first element,
			Node l_temp = m_NodeDA.get(m_NodeDA.size() - 1);
			m_NodeDA.set(0 , l_temp);
			
			// remove last element
			m_NodeDA.remove(m_NodeDA.size() -1);
			
			DownwardValidity(0);
			return l_ReturnNode;
		}
		return null;
	}
	
	public Node GetLeftChildNode(int i_ParentIndex)
	{
		int l_LeftChildIndex = (i_ParentIndex + 1) * 2;
		if(m_NodeDA.size() < l_LeftChildIndex)
		{
			return m_NodeDA.get(l_LeftChildIndex - 1);
		}
		else
			return null;
	}
	
	public Node GetRightChildNode(int i_ParentIndex)
	{
		int l_RightChildIndex = (i_ParentIndex + 1) * 2 + 1;
		if(m_NodeDA.size() < l_RightChildIndex)
		{
			return m_NodeDA.get(l_RightChildIndex - 1);
		}
		else
			return null;
	} 
	
	public Node GetParentNode(int i_ChildNodeIndex)
	{
		int l_ParentIndex = (i_ChildNodeIndex + 1)/2;
		if(m_NodeDA.size() < l_ParentIndex)
		{
			return m_NodeDA.get(l_ParentIndex - 1);
		}
		else
			return null;	
	}
	
	public void DownwardValidity(int i_ParentIndex)
	{
		if(m_NodeDA.size() > i_ParentIndex)
		{
			int l_LeftChildIndex = (i_ParentIndex + 1) * 2 - 1;
			int l_RightChildIndex = (i_ParentIndex + 1) * 2 ;
			
			if(IsValidIndex(i_ParentIndex))
			{
				Node l_CurrentNode 		= m_NodeDA.get(i_ParentIndex);
				Node l_LeftChildNode 	= GetLeftChildNode(l_LeftChildIndex);
				Node l_RightChildNode 	= GetRightChildNode(l_RightChildIndex);
				
				//No Child Exists
				if(l_LeftChildNode == null)
					return;
				//only left child exists
				if(l_RightChildNode == null && l_CurrentNode.GetWeight() < l_LeftChildNode.GetWeight())
				{
					SwapNode(i_ParentIndex, l_LeftChildIndex);
					return;
				}
				
				//left child is biggest
				if(l_LeftChildNode.GetWeight() > l_RightChildNode.GetWeight())
				{
					if(l_CurrentNode.GetWeight() < l_LeftChildNode.GetWeight())
					{
						SwapNode(i_ParentIndex, l_LeftChildIndex);
						DownwardValidity(l_LeftChildIndex);
					}
				}
				//right child is biggest
				else
				{
					if(l_CurrentNode.GetWeight() < l_RightChildNode.GetWeight())
					{
						SwapNode(i_ParentIndex, l_RightChildIndex);
						DownwardValidity(l_RightChildIndex);
					}
				}
				
			}
		}
	}
	
	
	public void UpwardValidity(int i_ChildIndex)
	{
		if(i_ChildIndex == 0)
			return;
		if(IsValidIndex(i_ChildIndex))
		{
			int l_ParentIndex = (i_ChildIndex + 1)/2 - 1;
			
			//compare child and parent
			Node l_CurrentNode 	=	m_NodeDA.get(i_ChildIndex);
			Node l_ParentNode	=	m_NodeDA.get(l_ParentIndex);
			
			if(l_ParentNode.GetWeight() < l_CurrentNode.GetWeight())
			{
				SwapNode(i_ChildIndex,l_ParentIndex);
				UpwardValidity(l_ParentIndex);
			}
		
		}
	}
	
	private boolean IsValidIndex(int i_Index)
	{
		return m_NodeDA.size() > i_Index; 
	}
	
	private void SwapNode(int i_NodeIndex1, int i_NodeIndex2)
	{
		if(i_NodeIndex1 < m_NodeDA.size() && i_NodeIndex2 < m_NodeDA.size())
		{
			Node l_Temp = m_NodeDA.get(i_NodeIndex1);
			m_NodeDA.set(i_NodeIndex1, m_NodeDA.get(i_NodeIndex2));
			m_NodeDA.set(i_NodeIndex2,l_Temp);
		}
	}
}
