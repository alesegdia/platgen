package com.alesegdia.platgen.tilemap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class TileMapRenderer extends JComponent {
	
	private TileMap map;
	private Dimension dimension;
	private int tilesize = 4;
	
	public TileMapRenderer(TileMap map)
	{
		this.dimension = new Dimension(map.cols * tilesize, map.rows * tilesize);
		this.map = map;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for( int i = 0; i < this.map.cols; i++ )
		{
			for( int j = 0; j < this.map.rows; j++ )
			{
				Color c = Color.magenta;
				switch( map.Get(j, i) )
				{
				case TileType.FREE:	c = Color.white; 		break;
				case TileType.DOOR: 	c = Color.yellow; 		break;
				case TileType.DOORL: 	c = Color.cyan; 		break;
				case TileType.DOORH: 	c = Color.magenta;		break;
				case TileType.USED: 	c = Color.lightGray; 	break;
				case TileType.WALL: 	c = Color.green; 		break;
				}
				g.setColor(c);
				g.fillRect(i * tilesize, j * tilesize, tilesize, tilesize);
			}
		}
		
		/*
		g.setColor(Color.lightGray);
		for( int i = 0; i < this.getWidth(); i += tilesize ) {
			g.drawLine(0, i, this.getWidth(), i);
		}
		for( int i = 0; i < this.getWidth(); i += tilesize ) {
			g.drawLine(i, 0, i, this.getWidth());
		}
		*/
	}

	public Dimension getPreferredSize() {
		return this.dimension;
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
	
	public void Show()
	{
		JFrame mainFrame = new JFrame("Map renderer");
		mainFrame.getContentPane().add(this);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	public void setTileSize(int i) {
		this.tilesize = i;
		this.dimension = new Dimension(map.cols * tilesize, map.rows * tilesize);
	}

}
