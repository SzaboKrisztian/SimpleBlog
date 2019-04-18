USE MyBlog;

INSERT INTO Users VALUES(NULL, 'Chris', NULL, NULL);

INSERT INTO blogentrycontent VALUE (NULL, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam tristique magna sit amet ultricies ornare. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur euismod sem velit, nec tempor dui lacinia sed. In hac habitasse platea dictumst. Duis ac auctor libero. Aenean posuere neque tortor, blandit fermentum diam interdum at. Suspendisse posuere porttitor erat et consequat. Quisque vel dolor cursus, ornare mi sit amet, faucibus purus. Aenean in velit nisl. Aliquam condimentum nunc eget eros auctor placerat. Donec condimentum aliquam semper. Nunc at nisi ac dui ornare placerat at nec eros. Aliquam enim nisl, aliquam quis nunc quis, ornare bibendum felis. Ut condimentum aliquam urna in tempus. Nam non neque aliquam, ultrices felis ac, sodales justo. Ut eget mi mollis dolor luctus rhoncus nec sit amet dolor.
Aenean laoreet et enim eget feugiat. Donec volutpat tortor eu neque ultrices fringilla. Interdum et malesuada fames ac ante ipsum primis in faucibus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Praesent efficitur nibh justo, non ullamcorper velit fermentum varius. Mauris cursus felis convallis ligula posuere, nec imperdiet arcu tincidunt. Duis nec velit eget nunc porttitor dictum non vitae elit. Morbi cursus massa ac erat pretium, entryId porta sem laoreet. Aliquam erat volutpat. Mauris varius sagittis tempor. Proin viverra magna in est pulvinar imperdiet. Fusce suscipit ullamcorper mattis. Phasellus ultrices dolor eget tellus lobortis, nec sagittis urna maximus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.
Sed ultrices vestibulum justo, sed tempus neque. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Praesent ultricies urna mollis, fermentum dolor vitae, iaculis tortor. Fusce porta lorem at ex mattis, ut ultrices mauris euismod. Proin laoreet placerat aliquet. Nunc nec purus elit. Donec blandit, elit eu egestas lobortis, odio ipsum dapibus nunc, et placerat ligula magna ac diam. Nullam scelerisque tortor nec nulla aliquet luctus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus, eros maximus placerat semper, dolor leo posuere arcu, sed scelerisque eros orci entryId est. Sed non urna entryId nibh malesuada tempor. Duis entryId dui tincidunt magna venenatis mollis. Etiam dui ex, ultrices vitae ornare sit amet, hendrerit sed sapien. Quisque euismod ipsum consectetur neque maximus aliquam. Mauris mollis felis vel gravida egestas.
Aenean entryId facilisis est. Cras quis ipsum varius, mollis tellus at, volutpat quam. Maecenas arcu ipsum, pharetra a blandit auctor, vehicula eu ipsum. Aenean mattis urna ac iaculis faucibus. Proin massa enim, finibus molestie erat ac, sagittis rhoncus nisi. Pellentesque faucibus luctus lectus vitae placerat. Nullam eget euismod augue. Donec orci metus, congue et egestas vitae, varius nec est.
Nam sit amet arcu ipsum. Pellentesque interdum laoreet urna, eu elementum lacus dignissim vel. Donec sapien nisi, ultrices accumsan turpis ac, tristique iaculis arcu. Praesent ut lectus in odio luctus fringilla sit amet at sem. Pellentesque faucibus vulputate interdum. Mauris efficitur nibh quis sapien faucibus auctor. Donec lobortis, lectus ut vestibulum lobortis, mi dolor laoreet nibh, vitae eleifend justo justo in arcu. Quisque nec ante tincidunt ipsum pulvinar cursus sit amet sit amet nulla. Aenean vestibulum eros in arcu egestas finibus. Phasellus quis metus quis turpis euismod rutrum at ac magna. Phasellus commodo tincidunt ligula, non ornare ex tempor tincidunt. ');

INSERT INTO blogentry(authorId, title, contentId) VALUES ((SELECT entryId FROM users WHERE username = 'Chris'),
                              'And now, something completely different!', 3);

INSERT INTO blogentrycontent VALUE (NULL, 'This is a rather short blog post. What a waste of screen real estate, innit?');

INSERT INTO blogentry(authorId, title, contentId) VALUES ((SELECT entryId FROM users WHERE username = 'Chris'),
                              'A short one', (SELECT entryId FROM blogentrycontent WHERE content LIKE 'This%'));