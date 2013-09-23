package edu.sjsu.cmpe.library.domain;
import java.util.Date;

public class Book {
    private long isbn;
    private String title;
    private Date pubDate;
    private String language;
    private int numPages;
    private String status = "available";
    /*add more fields here
    ISBN (Key) # You will generate this key.
   Title (Required field)
 Publication Date (Required field)
Language (Optional field)
Number of Pages (Optional field)
Status {available, checked-out, in-queue, or lost} # default to ‘available’
     */
    /**
     * @return the isbn
     */
    public long getIsbn() {
	return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }
    
    public Date getPublicationDate() {
    	return pubDate;
        }
    
    public void setPublicationDate(Date date) {
    	this.pubDate = date;
        }
    
    public String getLanguage() {
    	return language;
        }

        /**
         * @param title
         *            the title to set
         */
        public void setLanguage(String language) {
    	this.language = language;
        }
        public int getNumPages() {
        	return numPages;
            }

            /**
             * @param isbn
             *            the isbn to set
             */
            public void setNumPages(int numPages) {
        	this.numPages = numPages;
            }
            
            public String getStatus() {
            	return status;
                }

                /**
                 * @param title
                 *            the title to set
                 */
                public void setStatus(String status) {
            	this.status = status;
                }
}
