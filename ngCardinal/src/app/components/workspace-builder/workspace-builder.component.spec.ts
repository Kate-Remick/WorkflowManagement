import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkspaceBuilderComponent } from './workspace-builder.component';

describe('WorkspaceBuilderComponent', () => {
  let component: WorkspaceBuilderComponent;
  let fixture: ComponentFixture<WorkspaceBuilderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WorkspaceBuilderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WorkspaceBuilderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
