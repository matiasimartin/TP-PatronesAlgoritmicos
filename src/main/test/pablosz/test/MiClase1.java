package pablosz.test;

import pablosz.ann.NotPersistable;
import pablosz.ann.Persistable;

@Persistable
public class MiClase1 extends pablosz.test.MiClase1Base
{
	private int att1;
	private int att2;
	private int att3;
	
	@NotPersistable
	private Integer att4;
	
	public MiClase1(int a,int b,int c)
	{
		att1=a;
		att2=b;
		att3=c;
		att4 = 999; // no se persiste
	}
	
	public int getAtt1()
	{
		return att1;
	}
	public void setAtt1(int att1)
	{
		this.att1=att1;
	}
	public int getAtt2()
	{
		return att2;
	}
	public void setAtt2(int att2)
	{
		this.att2=att2;
	}
	public int getAtt3()
	{
		return att3;
	}
	public void setAtt3(int att3)
	{
		this.att3=att3;
	}	
	public Integer getAtt4()
	{
		return att4;
	}
}
